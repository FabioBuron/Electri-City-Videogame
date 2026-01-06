package system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import component.ElectricComponent
import ktx.ashley.get
import ktx.ashley.allOf

class ElectricSystem : IteratingSystem(allOf(ElectricComponent::class).get()) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        // Obtenemos el componente eléctrico de la entidad de forma segura con LibKTX
        val electric = entity[ElectricComponent.mapper] ?: return

        if (electric.isSource) {
            // Si es una fuente (batería/planta), quizás recupere energía con el tiempo
            electric.powerAmount += 5f * deltaTime
            println("Generando energía: ${electric.powerAmount}")
        } else {
            // Si es un consumidor (bombilla/fábrica), gasta energía
            if (electric.powerAmount > 0) {
                electric.powerAmount -= 2f * deltaTime
            }
        }
    }
}
