package com.example.deekshasharma.pennyapp.model;

public class IconWithTitleItem {


        private String title;
        private int icon;

        public IconWithTitleItem(){}

        public IconWithTitleItem(String title, int icon){
            this.title = title;
            this.icon = icon;
        }

        public String getTitle(){
            return this.title;
        }

        public int getIcon(){
            return this.icon;
        }



        public void setTitle(String title){
            this.title = title;
        }

        public void setIcon(int icon){
            this.icon = icon;
        }

    }
