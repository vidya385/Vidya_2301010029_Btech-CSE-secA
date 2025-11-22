print("Script 3: Reading previous outputs...\n")

try:
    with open("output1.txt", "r") as f1:
        print("Output from script 1:")
        print(f1.read())

    with open("output2.txt", "r") as f2:
        print("Output from script 2:")
        print(f2.read())

except FileNotFoundError:
    print("Previous script outputs not found.")

print("\nScript 3: Done!")
