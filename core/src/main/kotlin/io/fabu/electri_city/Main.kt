package io.fabu.electri_city

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.async.KtxAsync

class Main : KtxGame<KtxScreen>() {
    val batch: Batch by lazy { SpriteBatch() }

    val engine: Engine by lazy { PooledEngine() }

    override fun create() {
        KtxAsync.initiate()

        addScreen(FirstScreen(this))
        setScreen<FirstScreen>()
    }

    override fun dispose() {
        batch.dispose()
        super.dispose()
    }
}
