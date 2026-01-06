package utilities

import com.badlogic.ashley.core.Entity
import component.TransformComponent

class ZComparator : Comparator <Entity> {
    override fun compare(a: Entity, b: Entity): Int {
        val zA = TransformComponent.mapper.get(a).z
        val zB = TransformComponent.mapper.get(b).z
        return zA.compareTo(zB)
    }

}
