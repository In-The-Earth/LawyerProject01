package Model;


import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import program.DBhelper;
import program.LawyerScheduleRequestController;
import program.LawyerScheduleRequestDetailController;
import program.Main;

import javax.imageio.IIOException;
import java.io.IOException;

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
    private String des;
    private String lawyer_name;
    private String lawyer_username;
    private String client_name;
    private Client client;
    private Button detail;
    private Button delete_lawyer;
    private String time_toString;

    public Schedule(int id, int client_id, int lawyer_id, String type_case, String status, String type_where, String time, String day, String id_sup, String des) {
        this.id = id;
        this.client_id = client_id;
        this.lawyer_id = lawyer_id;
        this.type_case = type_case;
        this.status = status;
        this.type_where = type_where;
        this.time = time;
        this.day = day;
        this.id_sup = id_sup;
        this.des = des;
    }

    public Schedule(String type_case, String status, String type_where, String time, String day, String lawyer_name) {
        this.type_case = type_case;
        this.status = status;
        this.type_where = type_where;
        this.time = time;
        this.day = day;
        this.lawyer_name = lawyer_name;
    }

    public Schedule(int id,int lawyer_id, String lawyer_name,String id_sup) {
        this.id = id;
        this.lawyer_id = lawyer_id;
        this.lawyer_name = lawyer_name;
        this.id_sup = id_sup;
        this.delete_lawyer = new Button("delete");
        this.delete_lawyer.setOnAction(event -> {
            String[] ids = id_sup.split(":");
            String result = "";
            for(String u : ids){
                if(!u.equals(lawyer_id)){
                    if(result == ""){
                        result += u;
                    }else {
                        result += ":" + u;
                    }
                }
            }
            DBhelper.update_ID_sup(id,result);
        });
    }

    public Schedule(String type_case, String type_where, String time, String day, String client_name, Client client, String lawyer_username) {
        this.type_case = type_case;
        this.type_where = type_where;
        this.time = time;
        this.day = day;
        this.client_name = client_name;
        this.lawyer_username = lawyer_username;
        this.time_toString = getDT_toString();
        System.out.println(getDT_toString());
        this.detail = new Button("Info");
        this.detail.setOnAction(event -> {
            try {
                FXMLLoader loader = Main.getLoader(LawyerScheduleRequestController.class,"lawyerScheduleRequestDetail.fxml");
                Main.change_scene(loader,LawyerScheduleRequestController.class,detail,"lawyerScheduleRequestDetail.fxml");
                LawyerScheduleRequestDetailController c = loader.getController();
                c.setDay(day);
                c.setTime(time);
                c.setClient(client);
                c.setUsername(lawyer_username);
            }catch (Exception e){
                System.out.println(e);
            }
        });
    }


    public String getClient_name() {
        return client_name;
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

    public String getDes() {
        return des;
    }

    public String getLawyer_name() {
        return lawyer_name;
    }

    public Button getDetail() {
        return detail;
    }

    public Button getDelete_lawyer() {
        return delete_lawyer;
    }

    public String getTime_toString() {
        return time_toString;
    }

    public String getDT_toString(){
        String result = "";
        if(time.charAt(0) == 'm') {
//            result = result + "monday ";
//            System.out.println("check1");
//            for(int i = 8; i <= 15; i++){
//                String j =  Integer.toString(i);
//                String h = String.valueOf(time.charAt(1));
//                if (h.equals(j)){
//                    System.out.println("true");
//                    result = result + i + ".00-" + (i+1) +".00";
//                }
//            }
            if (time.charAt(1) == '8') {
                return "monday 8.00-9.00";
            } else if (time.charAt(1) == '9') {
                return "monday 9.00-10.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '0') {
                return "monday 10.00-11.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '1') {
                return "monday 11.00-12.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '2') {
                return "monday 12.00-13.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '3') {
                return "monday 13.00-14.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '4') {
                return "monday 14.00-15.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '5') {
                return "monday 15.00-16.00";
            }
        }
        else if(time.charAt(0) == 'w') {
            if (time.charAt(1) == '8') {
                return "wednesday 8.00-9.00";
            } else if (time.charAt(1) == '9') {
                return "wednesday 9.00-10.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '0') {
                return "wednesday 10.00-11.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '1') {
                return "wednesday 11.00-12.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '2') {
                return "wednesday 12.00-13.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '3') {
                return "wednesday 13.00-14.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '4') {
                return "wednesday 14.00-15.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '5') {
                return "wednesday 15.00-16.00";
            }
        }
        else if(time.charAt(0) == 'f') {
            if (time.charAt(1) == '8') {
                return "friday 8.00-9.00";
            } else if (time.charAt(1) == '9') {
                return "friday 9.00-10.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '0') {
                return "friday 10.00-11.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '1') {
                return "friday 11.00-12.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '2') {
                return "friday 12.00-13.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '3') {
                return "friday 13.00-14.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '4') {
                return "friday 14.00-15.00";
            } else if (time.charAt(1) == '1' & time.charAt(2) == '5') {
                return "friday 15.00-16.00";
            }
        }else if(time.charAt(0) == 't' & time.charAt(1) == 'u') {
            if (time.charAt(2) == '8') {
                return "tuesday 8.00-9.00";
            } else if (time.charAt(2) == '9') {
                return "tuesday 9.00-10.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '0') {
                return "tuesday 10.00-11.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '1') {
                return "tuesday 11.00-12.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '2') {
                return "tuesday 12.00-13.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '3') {
                return "tuesday 13.00-14.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '4') {
                return "tuesday 14.00-15.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '5') {
                return "tuesday 15.00-16.00";
            }
        }
        else if(time.charAt(0) == 't' & time.charAt(1) == 'h') {
            if (time.charAt(2) == '8') {
                return "thursday 8.00-9.00";
            } else if (time.charAt(2) == '9') {
                return "thursday 9.00-10.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '0') {
                return "thursday 10.00-11.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '1') {
                return "thursday 11.00-12.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '2') {
                return "thursday 12.00-13.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '3') {
                return "thursday 13.00-14.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '4') {
                return "thursday 14.00-15.00";
            } else if (time.charAt(2) == '1' & time.charAt(3) == '5') {
                return "thursday 15.00-16.00";
            }
        }




//        else if(time.charAt(0) == 'w') {
//            result = result + "wednesday ";
//            for(int i = 8; i <= 15; i++){
//                String j =  Integer.toString(i);
//                String h = String.valueOf(time.charAt(1));
//                if (h.equals(j)){
//                    System.out.println("true");
//                    result = result + i + ".00-" + (i+1) +".00";
//                }
//            }
//        }else if(time.charAt(0) == 'f') {
//            result = result + "friday ";
//            for(int i = 8; i <= 15; i++){
//                String j =  Integer.toString(i);
//                String h = String.valueOf(time.charAt(1));
//                if (h.equals(j)){
//                    System.out.println("true");
//                    result = result + i + ".00-" + (i+1) +".00";
//                }
//            }
//        }else if(time.charAt(0) == 't' & time.charAt(1) == 'u') {
//            result = result + "tuesday ";
//            for(int i = 8; i <= 15; i++){
//                String j =  Integer.toString(i);
//                String h = String.valueOf(time.charAt(2));
//                if (h.equals(j)){
//                    System.out.println("true");
//                    result = result + i + ".00-" + (i+1) +".00";
//                }
//            }
//        }
//        else if(time.charAt(0) == 't' & time.charAt(1) == 'h') {
//            result = result + "thursday ";
//            for(int i = 8; i <= 15; i++){
//                String j =  Integer.toString(i);
//                String h = String.valueOf(time.charAt(2));
//                if (h.equals(j)){
//                    System.out.println("true");
//                    result = result + i + ".00-" + (i+1) +".00";
//                }
//            }
//        }
        return result;
    }
}
