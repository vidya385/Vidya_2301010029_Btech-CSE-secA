from multiprocessing import Process, Pipe

def child_process(conn):
    message = conn.recv()
    print("Child received:", message)
    conn.close()

if __name__ == '__main__':
    parent_conn, child_conn = Pipe()
    
    p = Process(target=child_process, args=(child_conn,))
    p.start()
    
    parent_conn.send("Hello from parent process!")
    parent_conn.close()
    
    p.join()
