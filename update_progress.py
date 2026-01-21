import re

MARKDOWN_FILE = "README.md"

with open(MARKDOWN_FILE, "r") as f:
    content = f.read()

# Extract all checkboxes
tasks = re.findall(r"- \[( |x)\]", content, re.IGNORECASE)
total = len(tasks)
completed = tasks.count("x") + tasks.count("X")
percent = (completed / total * 100) if total > 0 else 0
progress_line = f"**Progress: {percent:.2f}% completed ({completed}/{total})**"

# Replace between markers
new_content = re.sub(
    r"<!-- progress:start -->.*?<!-- progress:end -->",
    f"<!-- progress:start -->\n{progress_line}\n<!-- progress:end -->",
    content,
    flags=re.DOTALL
)

with open(MARKDOWN_FILE, "w") as f:
    f.write(new_content)

print("Updated progress.")
