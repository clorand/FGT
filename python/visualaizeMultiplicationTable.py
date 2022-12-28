import matplotlib.pyplot as plot
import numpy as np
 
file_data = np.loadtxt("../DiagonalTable.txt", dtype=int)
print(file_data)


n = 200
 
plot.matshow(np.unwrap(file_data[0:1320, 0:1320]-11, period=1320))
 
plot.show()
