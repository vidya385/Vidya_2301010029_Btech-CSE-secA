print("Script 2: Running math calculations...")

result = sum([i for i in range(1, 11)])  

print(f"Script 2: Sum of 1 to 10 is {result}")

with open("output2.txt", "w") as f:
    f.write(f"Sum from script 2: {result}\n")

print("Script 2: Completed!")
