import matplotlib.pyplot as plot
import numpy as np
 
# StringIO behaves like a file object
file_data = np.loadtxt("../example1.txt", dtype=int)
print(file_data)

 
plot.matshow(file_data)
 
plot.show()