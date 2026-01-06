package component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor

class TextureComponent: Component, Pool.Poolable {
    var region: TextureRegion? = null

    override fun reset() {
        region = null
    }
companion object {
    val mapper = mapperFor<TextureComponent>()
}
}
