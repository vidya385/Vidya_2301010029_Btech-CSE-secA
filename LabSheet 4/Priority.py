def priority_scheduling(processes):
    processes.sort(key=lambda x: x[1])
    ready = []
    time = 0

    print("\n--- Priority Scheduling ---")
    print("PID\tAT\tBT\tPR\tWT\tTAT")

    while processes or ready:
        while processes and processes[0][1] <= time:
            ready.append(processes.pop(0))

        if ready:
            ready.sort(key=lambda x: x[3])
            pid, at, bt, pr = ready.pop(0)

            wt = time - at
            tat = wt + bt
            time += bt

            print(f"{pid}\t{at}\t{bt}\t{pr}\t{wt}\t{tat}")
        else:
            time += 1
