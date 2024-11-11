package org.example.ui;

public class ViewMap {
    public static void main(String[] args) {
        new GraphDrawer(new NavigatorMockImpl()).draw();
    }
}
