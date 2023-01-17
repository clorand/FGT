import matplotlib.pyplot as plot
import numpy as np
 
file_data = np.loadtxt("../A0TransposeTable.txt", dtype=int)
print(file_data)


n = 200
 
plot.matshow(file_data[0:119, 0:119])
 
plot.show()
