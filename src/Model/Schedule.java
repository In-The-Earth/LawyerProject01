package Model;

public class Schedule {
    private int id;
    private int client_id;
    private int lawyer_id;
    private String type_case;
    private String status;
    private String type_where;
    private String time;
    private String day;
    private String id_sup;

    public Schedule(int id, int client_id, int lawyer_id, String type_case, String status, String type_where, String time, String day, String id_sup) {
        this.id = id;
        this.client_id = client_id;
        this.lawyer_id = lawyer_id;
        this.type_case = type_case;
        this.status = status;
        this.type_where = type_where;
        this.time = time;
        this.day = day;
        this.id_sup = id_sup;
    }

    public int getId() {
        return id;
    }

    public int getClient_id() {
        return client_id;
    }

    public int getLawyer_id() {
        return lawyer_id;
    }

    public String getType_case() {
        return type_case;
    }

    public String getStatus() {
        return status;
    }

    public String getType_where() {
        return type_where;
    }

    public String getTime() {
        return time;
    }

    public String getDay() {
        return day;
    }

    public String getId_sup() {
        return id_sup;
    }
}
