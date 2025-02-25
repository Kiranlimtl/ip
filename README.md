# WallE 🤖

> "WALL-E" - *Director's Cut*

**WallE** *frees your mind* of having to remember things you need to do. It's:
- **intuitive** and user-friendly
- **lightweight** and fast
- **designed** for productivity enthusiasts
- ~~Experimental features~~ *(coming soon)*

To get started:
1. [Download WallE](https://github.com/Kiranlimtl/ip/releases/tag/A-Release)
2. Run the app
3. Add your task using `event / deadline / todo`
4. Complete them

And the best part, WallE is **Free!** 😄

---

## Features:
- [x] Task management
- [x] Reminders
- [ ] Priority levels

### Sample Code:
For Java programmers, here’s a snippet of a task class:

```java
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

