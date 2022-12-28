import matplotlib.pyplot as plt
import numpy as np
from _operator import index


def index_transpose(index):
    if (index<121):
        return index
    else:
        k = index % 11
        j = int((index//11) % 11)
        i = int((index//121) % 11)    
        return int(121*i+11*k+j)


x = []


for index in range(11, 1331):
    x = np.append(x, index_transpose(index))
    
    
print(x)

plt.plot(x)
plt.show()

    
    