def fcfs(processes):
    
    processes.sort(key=lambda x: x[1])

    total_wt = 0
    total_tat = 0
    current_time = 0

    print("\n--- FCFS Scheduling ---")
    print("PID\tAT\tBT\tWT\tTAT")

    for p in processes:
        pid, at, bt, pr = p

        if current_time < at:
            current_time = at

        wt = current_time - at
        tat = wt + bt

        current_time += bt

        total_wt += wt
        total_tat += tat

        print(f"{pid}\t{at}\t{bt}\t{wt}\t{tat}")

    print("\nAverage WT =", total_wt / len(processes))
    print("Average TAT =", total_tat / len(processes))

if __name__ == "__main__":
    n = int(input("Enter number of processes: "))

    processes = []
    for i in range(n):
        pid = int(input(f"Enter PID for Process {i+1}: "))
        at = int(input("Enter Arrival Time: "))
        bt = int(input("Enter Burst Time: "))
        pr = 0  
        processes.append([pid, at, bt, pr])

    fcfs(processes)
