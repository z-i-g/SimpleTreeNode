class Vertex
{
    public int Value;
    public boolean Hit;
    public Vertex(int val)
    {
        Value = val;
        Hit = false;
    }
}

class SimpleGraph
{
    Vertex [] vertex;
    int [][] m_adjacency;
    int max_vertex;
    Stack<Integer> depthFirstSearchStack = new Stack<>();
    Queue<Integer> breadthFirstSearchQueue = new PriorityQueue<>();
    Set<Integer> breadthFirstSearchRoute = new LinkedHashSet<>();

    public SimpleGraph(int size)
    {
        max_vertex = size;
        m_adjacency = new int [size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex(int value)
    {
        Vertex addedVertex = new Vertex(value);
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == null) {
                vertex[i] = addedVertex;
                return;
            }
        }
        // ваш код добавления новой вершины
        // с значением value
        // в незанятую позицию vertex
    }

    // здесь и далее, параметры v -- индекс вершины
    // в списке  vertex
    public void RemoveVertex(int v)
    {
        if (v > vertex.length)
            return;
        vertex[v] = null;
        for (int i = 0; i < vertex.length; i++) {
            m_adjacency[v][i] = 0;
            m_adjacency[i][v] = 0;
        }
        // ваш код удаления вершины со всеми её рёбрами
    }

    public boolean IsEdge(int v1, int v2)
    {
        if (v1 > vertex.length || v2 > vertex.length)
            return false;

        return m_adjacency[v1][v2] == 1 && m_adjacency[v2][v1] ==1;
        // true если есть ребро между вершинами v1 и v2
    }

    public void AddEdge(int v1, int v2)
    {
        if (v1 > vertex.length || v2 > vertex.length)
            return;
        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
        // добавление ребра между вершинами v1 и v2
    }

    public void RemoveEdge(int v1, int v2)
    {
        if (v1 > vertex.length || v2 > vertex.length)
            return;
        m_adjacency[v1][v2] = 0;
        m_adjacency[v2][v1] = 0;
        // удаление ребра между вершинами v1 и v2
    }

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo)
    {
        ArrayList<Vertex> routeVertexes = new ArrayList<>();
        if (VFrom > vertex.length || VTo > vertex.length)
            return routeVertexes;
        depthFirstSearchStack.clear();
        for (Vertex v : vertex) {
            v.Hit = false;
        }

        Stack<Integer> routeVertexesIndexes = depthFirstSearchRecursively(VFrom, VTo);
        for (Integer routeVertexesIndex : routeVertexesIndexes) {
            routeVertexes.add(vertex[routeVertexesIndex]);
        }

        // Узлы задаются позициями в списке vertex.
        // Возвращается список узлов -- путь из VFrom в VTo.
        // Список пустой, если пути нету.
        return routeVertexes;
    }

    public Stack<Integer> depthFirstSearchRecursively(int VFrom, int VTo)
    {
        Vertex currentVertex = vertex[VFrom];
        currentVertex.Hit = true;
        depthFirstSearchStack.push(VFrom);

        for (int i = 0; i < vertex.length; i++) {
            if (m_adjacency[VFrom][i] == 1 && i == VTo) {
                depthFirstSearchStack.push(i);
                return depthFirstSearchStack;
            }
            if (m_adjacency[VFrom][i] == 1 && !vertex[i].Hit) {
                return depthFirstSearchRecursively(i, VTo);
            }
        }

        depthFirstSearchStack.pop();
        if (!depthFirstSearchStack.isEmpty())
            return depthFirstSearchRecursively(depthFirstSearchStack.pop(), VTo);

        return depthFirstSearchStack;
    }

    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo)
    {
        ArrayList<Vertex> routeVertexes = new ArrayList<>();

        if (VFrom > vertex.length || VTo > vertex.length)
            return routeVertexes;

        breadthFirstSearchQueue.clear();
        for (Vertex v : vertex) {
            v.Hit = false;
        }
        breadthFirstSearchRoute.add(VFrom);
        Set<Integer> routeVertexesIndexes = breadthFirstSearchRecursively(VFrom, VTo);
        for (Integer routeVertexesIndex : routeVertexesIndexes) {
            routeVertexes.add(vertex[routeVertexesIndex]);
        }
        // Узлы задаются позициями в списке vertex.
        // Возвращается список узлов -- путь из VFrom в VTo.
        // Список пустой, если пути нету.
        return routeVertexes;
    }

    private Set<Integer> breadthFirstSearchRecursively(int VFrom, int VTo)
    {
        Vertex currentVertex = vertex[VFrom];
        currentVertex.Hit = true;

        for (int i = 0; i < vertex.length; i++) {
            if (m_adjacency[VFrom][i] == 1 && !vertex[i].Hit && i == VTo) {
                breadthFirstSearchRoute.add(VFrom);
                breadthFirstSearchRoute.add(i);
                return breadthFirstSearchRoute;
            }
            if (m_adjacency[VFrom][i] == 1 && !vertex[i].Hit) {
                vertex[i].Hit = true;
                breadthFirstSearchQueue.add(i);
                breadthFirstSearchRoute.add(VFrom);
            }
        }

        if (!breadthFirstSearchQueue.isEmpty())
            return breadthFirstSearchRecursively(breadthFirstSearchQueue.remove(), VTo);

        return breadthFirstSearchRoute;
        // Узлы задаются позициями в списке vertex.
        // Возвращается список узлов -- путь из VFrom в VTo.
        // Список пустой, если пути нету.
    }
}