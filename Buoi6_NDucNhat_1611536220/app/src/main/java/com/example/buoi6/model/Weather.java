package com.example.buoi6.model;

import com.example.buoi6.model.Location;

public class Weather {
    public Location location;
    public StatePresent statePresent = new StatePresent();
    public Temperature temperature = new Temperature();
    public Wind wind = new  Wind();
    public  String image;
    public  class  StatePresent {
        private String ImageName;
        private float AlmosphericPressure;
        private float Moisture;


        public String getImageName() {
            return ImageName;
        }

        public void setImageName(String imageName) {
            ImageName = imageName;
        }

        public float getAlmosphericPressure() {
            return AlmosphericPressure;
        }

        public void setAlmosphericPressure(float almosphericPressure) {
            AlmosphericPressure = almosphericPressure;
        }

        public float getMoisture() {
            return Moisture;
        }

        public void setMoisture(float moisture) {
            Moisture = moisture;
        }
    }
    public class Temperature{
        private  float Tp;

        public float getTp() {
            return Tp;
        }

        public void setTp(float tp) {
            Tp = tp;
        }
    }
    public class  Wind{
        private  float speed;

        public float getSpeed() {
            return speed;
        }

        public void setSpeed(float speed) {
            this.speed = speed;
        }
    }

}
