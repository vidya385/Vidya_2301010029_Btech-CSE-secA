import subprocess
import os

scripts = ['script1.py', 'script2.py', 'script3.py']

for script in scripts:
    if os.path.exists(script):
        print(f"\n🔹 Executing {script}...")
       
        result = subprocess.run(['python', script])
        
        if result.returncode == 0:
            print(f"{script} completed successfully.\n")
        else:
            print(f"Error executing {script} (Return code: {result.returncode})\n")
    else:
        print(f"File not found: {script}")
