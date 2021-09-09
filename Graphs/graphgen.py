import random
def graph_gen(m,n):
    graph = [[
            round(random.random()+0.2) for i in range(m)
            ] for j in range(n)]
    for i in graph:
            print(*i)

print("===printing random directed adjacency matrix===")
graph_gen(10,10)
