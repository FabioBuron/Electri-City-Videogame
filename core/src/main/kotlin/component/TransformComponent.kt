package component
import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor

class TransformComponent : Component, Pool.Poolable {
    var x: Float = 0f
    var y: Float = 0f
    var z: Float = 0f
    override fun reset() {
        x = 0f
        y = 0f
        z = 0f
    }

    companion object{
        val mapper = mapperFor<TransformComponent>()
    }
}
