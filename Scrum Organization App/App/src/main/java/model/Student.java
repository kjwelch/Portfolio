package model;

/**
 * A student has a backlog, a sprint, a sprint archive, a name, and a username
 *
 * @author Thomas Breimer
 * @version 5/22/23
 */


public class Student {

    private Sprint sprint;
    private String name;

    int id;

    Backlog backlog;

    /**
     * Default constructor for student

     * @param name student's name
     * @param id student's id
     */
    public Student(String name, int id){
        this.id = id;
        this.name = name;
        this.backlog = new Backlog(id);
        this.sprint = new Sprint("Untitled Sprint", id);
    }

    /**
     *
     * @return the student's name
     */
    public String getName() {
        return name;
    }


    /**
     *
     * @return the student's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the student's sprint
     * @param sprint sprint to set
     */
    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    /**
     * Sets the student's backlog
     * @param backlog backlog to set
     */
    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    /**
     *
     * @return the student's sprint
     */
    public Sprint getSprint() {
        return sprint;
    }

    /**
     *
     * @return the student's backlog
     */
    public Backlog getBacklog() {
        return backlog;
    }
}
