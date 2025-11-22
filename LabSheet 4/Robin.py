def round_robin(processes, quantum):
    queue = []
    time = 0
    remaining = {p[0]: p[2] for p in processes}
    processes.sort(key=lambda x: x[1])

    print("\n--- Round Robin Scheduling ---")
    print("PID\tWT\tTAT")

    wt = {}
    tat = {}

    while processes or queue:
        while processes and processes[0][1] <= time:
            queue.append(processes.pop(0))

        if not queue:
            time += 1
            continue

        pid, at, bt, pr = queue.pop(0)

        if remaining[pid] > quantum:
            remaining[pid] -= quantum
            time += quantum
            queue.append([pid, at, bt, pr])
        else:
            time += remaining[pid]
            tat[pid] = time - at
            wt[pid] = tat[pid] - bt
            remaining[pid] = 0

    for pid in wt:
        print(f"{pid}\t{wt[pid]}\t{tat[pid]}")
