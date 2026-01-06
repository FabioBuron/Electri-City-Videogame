package system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.SortedIteratingSystem
import com.badlogic.gdx.graphics.g2d.Batch
import component.TextureComponent
import component.TransformComponent
import ktx.ashley.allOf
import utilities.ZComparator

class RenderSystem(private val batch: Batch) : SortedIteratingSystem (
    allOf(TransformComponent::class, TextureComponent::class).get(),
    ZComparator()
) {
    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transform =TransformComponent.mapper.get(entity)

        val texture = TextureComponent.mapper.get(entity)

        texture.region?.let {
            batch.draw(
                it,
                transform.x,
                transform.y
            )
        }  }
    override fun update(deltaTime: Float) {
        batch.begin()
        super.update(deltaTime)
        batch.end()
    }
}
