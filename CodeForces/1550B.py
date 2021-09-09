# 1010011 find max if point awarded as a*l + b
# https://codeforces.com/problemset/problem/1550/B

for i in range(int(input())):
    n, a, b = map(int, input().split())
    k = input()
    if b >= 0:
        print(n*a + n*b)
    else:
        c = max(k.count('01'), k.count('10'))+1
        print(a*n + c*b)
