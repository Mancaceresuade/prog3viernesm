# Comparación: AVL vs HashMap para el Problema GameScore

## Problema a Resolver
Encontrar los **k mejores jugadores** en un **rango de puntajes [p_min, p_max]** de manera eficiente.

## Solución con HashMap

### Estructura de Datos
```java
HashMap<Integer, List<Jugador>> puntajesJugadores;
// Clave: puntaje, Valor: lista de jugadores con ese puntaje
```

### Implementación
```java
public List<Jugador> buscarMejoresJugadoresEnRangoHashMap(int p_min, int p_max, int k) {
    List<Jugador> resultado = new ArrayList<>();
    
    // Recorrer todos los puntajes en el rango
    for (int puntaje = p_max; puntaje >= p_min && resultado.size() < k; puntaje--) {
        List<Jugador> jugadoresConPuntaje = puntajesJugadores.get(puntaje);
        if (jugadoresConPuntaje != null) {
            for (Jugador jugador : jugadoresConPuntaje) {
                if (resultado.size() >= k) break;
                resultado.add(jugador);
            }
        }
    }
    
    return resultado;
}
```

### Análisis de Complejidad

#### Tiempo
- **Búsqueda en HashMap**: O(1) promedio, O(n) peor caso
- **Recorrido del rango**: O(p_max - p_min + 1)
- **Complejidad total**: **O(p_max - p_min + k)**

#### Espacio
- **HashMap**: O(n) donde n es el número de jugadores
- **Listas por puntaje**: O(n) adicional

## Solución con AVL (implementación actual)

### Complejidad
- **Tiempo**: O(log n + k)
- **Espacio**: O(n)

## Comparación de Complejidades

### Caso 1: Rango pequeño de puntajes
**Ejemplo**: [1400, 1500] con k = 3

- **HashMap**: O(101 + 3) = O(104)
- **AVL**: O(log n + 3) = O(log n + 3)

**Para n = 1000:**
- HashMap: O(104)
- AVL: O(10 + 3) = O(13)

**Resultado**: AVL es mejor

### Caso 2: Rango grande de puntajes
**Ejemplo**: [100, 2000] con k = 5

- **HashMap**: O(1901 + 5) = O(1906)
- **AVL**: O(log n + 5) = O(log n + 5)

**Para n = 1000:**
- HashMap: O(1906)
- AVL: O(10 + 5) = O(15)

**Resultado**: AVL es significativamente mejor

### Caso 3: Rango muy grande
**Ejemplo**: [1, 10000] con k = 10

- **HashMap**: O(10000 + 10) = O(10010)
- **AVL**: O(log n + 10) = O(log n + 10)

**Para n = 1000:**
- HashMap: O(10010)
- AVL: O(10 + 10) = O(20)

**Resultado**: AVL es mucho mejor

## Ventajas y Desventajas

### HashMap

#### Ventajas:
- ✅ Acceso O(1) a jugadores por puntaje específico
- ✅ Fácil de implementar
- ✅ Eficiente para rangos muy pequeños

#### Desventajas:
- ❌ Complejidad O(rango + k) que puede ser O(n) o peor
- ❌ No mantiene ordenamiento natural
- ❌ Ineficiente para rangos grandes
- ❌ No aprovecha la estructura de datos ordenada

### AVL

#### Ventajas:
- ✅ Complejidad O(log n + k) independiente del rango
- ✅ Mantiene ordenamiento natural
- ✅ Eficiente para cualquier tamaño de rango
- ✅ Optimizaciones específicas del problema

#### Desventajas:
- ❌ Implementación más compleja
- ❌ Overhead de balanceo
- ❌ Acceso O(log n) en lugar de O(1)

## Análisis Matemático

### Fórmula de decisión
**HashMap es mejor solo si:**
```
p_max - p_min + k < log n + k
p_max - p_min < log n
```

**Donde:**
- `p_max - p_min + 1`: tamaño del rango de puntajes
- `log n`: altura del árbol AVL
- `k`: número de jugadores solicitados

### Ejemplo numérico
Para n = 1000 jugadores:
- log₂(1000) ≈ 10

**HashMap solo es mejor si:**
- Rango [1400, 1409] → 10 puntajes → HashMap gana
- Rango [1400, 1410] → 11 puntajes → AVL gana

## Conclusión

### ¿Puede un HashMap resolver en menor complejidad?

**SÍ, pero solo en casos muy específicos:**

1. **Rangos de puntajes muy pequeños** (≤ log n)
2. **k muy pequeño** comparado con el rango
3. **Distribución uniforme** de puntajes

### ¿Cuándo usar cada uno?

#### Usar HashMap si:
- Los rangos de puntajes son siempre pequeños
- Se necesita acceso O(1) por puntaje específico
- La implementación debe ser simple

#### Usar AVL si:
- Los rangos de puntajes pueden ser grandes
- Se necesita eficiencia garantizada O(log n + k)
- Se requiere ordenamiento natural
- Se quieren optimizaciones específicas del problema

### Recomendación para GameScore

**El AVL es la mejor opción** porque:
1. Los rangos de puntajes pueden variar significativamente
2. Se necesita eficiencia garantizada independiente del rango
3. El ordenamiento natural es importante
4. La complejidad O(log n + k) es óptima para el problema

**El HashMap solo sería mejor en escenarios muy limitados** donde los rangos de puntajes son siempre menores que log n.
