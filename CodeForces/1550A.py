# beautiful numbers, if a(i) = 1 or a(i)-1 or a(i)-2 in a
# https://codeforces.com/problemset/problem/1550/A

t = int(input())
 
def count(a, maximum):
    j = len(a)-1
    c = 0
    for i, val in enumerate(a):
        while j > i and a[i] + a[j] > maximum:
            j = j - 1
        c += max(j - i, 0)
 
    return c
 
for _ in range(t):
    n, l, r = map(lambda x: int(x), input().split())
    arr = list(map(lambda x: int(x), input().split()))
    arr.sort()
    print(count(arr, r) - count(arr, l - 1))