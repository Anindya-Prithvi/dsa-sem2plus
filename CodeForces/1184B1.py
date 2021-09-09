# Spaceship attack, Spaceship(s), Bases (b), attack power array, 
# defence and gold
# https://codeforces.com/problemset/problem/1184/B1


def bin_search_near(start,end,e):
    mid = (start + end) //2
    if end<start: return end
    if e==base_spec[mid][0]:
        return mid
    if e>base_spec[mid][0]: return bin_search_near(mid+1,end,e)
    if e<base_spec[mid][0]: return bin_search_near(start,mid-1,e)
    return mid

s,b = map(int,input().split())
arr = map(int,input().split())
base_spec = []
for i in range(b):
    base_spec.append(list(map(int,input().split())))
base_spec.sort(key = lambda x: x[0])
prev = 0
for i in range(b):
    base_spec[i][1] += prev
    prev = base_spec[i][1]
for i in arr:
    index = bin_search_near(0,b-1,i)
    if index==-1:
        print(0,end=" ")
        continue
    if base_spec[index][0]>i:
        print(base_spec[index-1][1],end =" ")
        continue
    print(base_spec[index][1],end=" ")
