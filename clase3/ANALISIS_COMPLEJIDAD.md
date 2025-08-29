# Análisis de Complejidad Temporal - buscarMejoresJugadoresEnRangoRec

## Método de Sustracción (Master Theorem)

### Ecuación de Recurrencia

Para el método `buscarMejoresJugadoresEnRangoRec`, la ecuación de recurrencia es:

```
T(n) = T(n/2) + O(1)  si el nodo está fuera del rango
T(n) = T(n/2) + T(n/2) + O(1)  si el nodo está dentro del rango
```

### Análisis por Casos

#### Caso 1: Nodo fuera del rango [p_min, p_max]
- **puntaje > p_max**: Solo se visita el subárbol izquierdo
- **puntaje < p_min**: Solo se visita el subárbol derecho

En ambos casos:
```
T(n) = T(n/2) + O(1)
```

#### Caso 2: Nodo dentro del rango [p_min, p_max]
Se visitan ambos subárboles (pero con parada temprana):
```
T(n) = T(n/2) + T(n/2) + O(1) = 2T(n/2) + O(1)
```

### Aplicación del Master Theorem

#### Para el Caso 1: T(n) = T(n/2) + O(1)
- **a = 1** (número de subproblemas)
- **b = 2** (factor de reducción del tamaño)
- **k = 0** (exponente del término no recursivo: O(1) = O(n⁰))

**Cálculo:**
- log_b(a) = log₂(1) = 0
- k = 0
- Como log_b(a) = k, estamos en el **Caso 2** del Master Theorem
- **Complejidad: O(log n)**

#### Para el Caso 2: T(n) = 2T(n/2) + O(1)
- **a = 2** (número de subproblemas)
- **b = 2** (factor de reducción del tamaño)
- **k = 0** (exponente del término no recursivo: O(1) = O(n⁰))

**Cálculo:**
- log_b(a) = log₂(2) = 1
- k = 0
- Como log_b(a) > k, estamos en el **Caso 1** del Master Theorem
- **Complejidad: O(n^(log₂(2))) = O(n¹) = O(n)**

### Análisis Realista con Optimizaciones

Sin embargo, el análisis anterior no considera las optimizaciones implementadas:

1. **Parada temprana**: `resultado.size() >= k`
2. **Altura del árbol AVL**: O(log n)
3. **Número máximo de nodos visitados**: k (jugadores solicitados)

### Complejidad Final

#### Mejor Caso
- Cuando todos los nodos están fuera del rango
- **T(n) = O(log n)**

#### Caso Promedio
- Considerando la altura del AVL y la parada temprana
- **T(n) = O(log n + k)**
- Donde:
  - O(log n): tiempo para navegar hasta el rango
  - O(k): tiempo para encontrar k jugadores

#### Peor Caso
- Cuando todos los nodos están en el rango y k es grande
- **T(n) = O(n)**
- Pero esto es muy improbable en la práctica

### Valores de a, b, k para cada caso:

#### Caso 1 (nodo fuera del rango):
- **a = 1** (1 subproblema)
- **b = 2** (tamaño se reduce a la mitad)
- **k = 0** (trabajo constante O(1))

#### Caso 2 (nodo dentro del rango):
- **a = 2** (2 subproblemas)
- **b = 2** (tamaño se reduce a la mitad)
- **k = 0** (trabajo constante O(1))

### Conclusión

La complejidad temporal real del método `buscarMejoresJugadoresEnRangoRec` es:

**O(log n + k)**

Donde:
- **n**: número total de jugadores en el árbol
- **k**: número de mejores jugadores solicitados

Esta complejidad es óptima para el problema, ya que:
1. No se pueden evitar los O(log n) pasos para navegar en el árbol AVL
2. No se pueden evitar los O(k) pasos para encontrar k jugadores
3. Las optimizaciones implementadas evitan visitar nodos innecesarios

