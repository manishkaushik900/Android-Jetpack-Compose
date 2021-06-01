package com.demo.jetpackcompose;

import androidx.core.app.Person;

public class PersonData {
    private String name;
    private String  gender;
    private boolean status;
    private String state;
    private String country;

//    create getters to get values
    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    //step 1: make the constructor private,
    // Constructor is private because now only way to intialize is through Builder
    private PersonData(Builder builder){
        this.name = builder.name;
        this.gender = builder.gender;
        this.status = builder.status;
    }

    public static class Builder{
        //Copy all the  fields into static Builder Class
        private String name;
        private String  gender;
        private boolean status;
        private String state;
        private String country;

        //if we want few fields mandatory, we can create a public constructor with those fields
        Builder(String state, String country){
            this.state = state;
            this.country = country;
        }

        //Now we create methods to set values to above field one by one
        //each time we will return Builder itself. So that it can be update with new required fields
        public Builder withName(String name){
            this.name =name;
            return this;
        }

        public Builder withGender(String gender){
            this.gender =gender;
            return this;
        }

        public Builder isStatus(boolean status){
            this.status =status;
            return this;
        }

        //we can create methods for other fields like above

        public PersonData build(){
            return new PersonData(this);
        }
    }
}

 class Main{

    public static void main(String[] args){
        PersonData data = new PersonData.Builder("Haryana","Delhi")
                .withName("Manish")
                .withGender("Male")
                .isStatus(true)
                .build();

        //Reusing a Builder
        //We can reuse a builder as per our needs
        PersonData.Builder builder = new PersonData.Builder("Haryana","Delhi");
        data = builder.withName("Manish")
                .withGender("Male")
                .isStatus(true).build();

        //lets say we only want a person with few data
        PersonData data2 = new PersonData.Builder("Haryana","Delhi")
                .withName("Manish")
                .build();

        System.out.println(data.getName());
        System.out.println(data.getCountry());
    }
}

