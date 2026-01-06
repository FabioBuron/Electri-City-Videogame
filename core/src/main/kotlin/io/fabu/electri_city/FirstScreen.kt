package io.fabu.electri_city

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import ktx.scene2d.*
import ktx.actors.*
import component.ElectricComponent
import component.TextureComponent
import component.TransformComponent
import ktx.app.KtxInputAdapter
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.ashley.entity
import ktx.ashley.with
import system.ElectricSystem
import system.RenderSystem
import ktx.scene2d.*

// AÑADIDO: KtxInputAdapter para poder detectar los clics
class FirstScreen(private val game: Main) : KtxScreen, KtxInputAdapter {

    private val worldWidth = 800f
    private val worldHeight = 480f

    private val camera = OrthographicCamera()
    private val viewport = FitViewport(worldWidth, worldHeight, camera)
    private val texture = Texture("logo.png")

    // Usaremos un vector temporal para no crear objetos nuevos cada segundo (evita basura en memoria)
    private val touchPosition = Vector3()
    // Escenario para la UI
    private val uiStage = Stage(FitViewport(800f, 480f))
    // Por ahora cargamos un skin vacío o básico (necesitarás un archivo .json de skin pronto)
    private val skin = Skin()
    override fun show() {

        camera.position.set(worldWidth / 2f, worldHeight / 2f, 0f)

        game.engine.addSystem(RenderSystem(game.batch))
        game.engine.addSystem(ElectricSystem())

        // Decimos que esta pantalla es la que maneja el ratón/clics
        Gdx.input.inputProcessor = this
        // Multiplexer: permite que el escenario de UI y el juego reciban clics
        val multiplexer = InputMultiplexer()
        multiplexer.addProcessor(uiStage) // La UI tiene prioridad
        multiplexer.addProcessor(this)    // Luego el juego
        Gdx.input.inputProcessor = multiplexer

        // CREAMOS LA UI DESCRIPTIVA
        uiStage.actors {
            // Definimos que esta tabla usará nuestro objeto skin
            table(skin = this@FirstScreen.skin) {
                top().left()
                setFillParent(true)

                // Ahora los hijos ya saben que deben usar ese skin
                label("Electri-City v0.1") {
                    it.pad(10f)
                }
                row()
                textButton("Construir Generador") {
                    onChange {
                        println("Modo Generador Activo")
                    }
                }
            }
        }
        // Creamos nuestra primera entidad (un Generador en el centro)

        game.engine.entity {
            with<TransformComponent> {
                x = worldWidth / 2f - 32f
                y = worldHeight / 2f - 32f
            }
            with<TextureComponent> { region = TextureRegion(texture) }
            with<ElectricComponent> {
                powerCapacity = 100f
                isPowered = true
            }
        }

    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        // Transformamos píxeles de pantalla a coordenadas del mundo virtual
        viewport.unproject(touchPosition.set(screenX.toFloat(), screenY.toFloat(), 0f))

        // Creamos una entidad en la posición del clic
        game.engine.entity {
            with<TransformComponent> {
                x = touchPosition.x - 32f
                y = touchPosition.y - 32f
                z = 2f // Un poco más "arriba" en profundidad
            }
            with<TextureComponent> { region = TextureRegion(texture) }
            with<ElectricComponent> {
                powerCapacity = 0f
                isPowered = false
            }
        }

        println("¡Construido en ${touchPosition.x}, ${touchPosition.y}!")
        return true
    }

    override fun render(delta: Float) {
        clearScreen(red = 0.1f, green = 0.1f, blue = 0.1f)
        camera.update()
        game.batch.projectionMatrix = camera.combined
        game.engine.update(delta)

        // Dibuja la UI encima del juego
        uiStage.act(delta)
        uiStage.draw()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height)
    }

    override fun hide() {
        Gdx.input.inputProcessor = null
    }

    override fun dispose() {
        uiStage.dispose()
        skin.dispose()
        texture.dispose()
    }
}
