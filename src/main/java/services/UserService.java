package services;

import models.User;

import java.io.*;

public class UserService {

    private final String filePath = "src/main/resources/users";
    private File userList;

    public UserService() {
        userList = new File(filePath + "/user_list.csv");
        if(!userList.exists()){
            try {
                userList.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean verify(User user){
        boolean match = false;
        FileReader fileReader;
        BufferedReader bufferedReader;

        try {
            fileReader = new FileReader(userList);
            bufferedReader = new BufferedReader(fileReader);


            while (bufferedReader.ready()){
                String[] currentUser = bufferedReader.readLine().split(",");
                if(user.getUsername().equals(currentUser[0])){
                    if(user.getPassword().equals(currentUser[1])){
                        match = true;
                        break;
                    }
                    else continue;
                }
                else continue;

            }

            bufferedReader.close();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return match;
    }

    public boolean addUser(User user) {
        boolean userAdded = false;
        File temp = new File(filePath + "/temp.csv");
        FileReader fileReader;
        FileWriter fileWriter;

        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;

        try {
            fileReader = new FileReader(userList);
            fileWriter = new FileWriter(temp);
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);

            temp.createNewFile();

            while (bufferedReader.ready()){
                bufferedWriter.write(bufferedReader.readLine());
                bufferedWriter.newLine();
            }

            bufferedWriter.write(user.getUsername() + "," + user.getPassword());
            userAdded = true;

            bufferedReader.close();
            bufferedWriter.flush();
            bufferedWriter.close();

            userList.delete();
            temp.renameTo(userList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        };
        return userAdded;
    }

}
