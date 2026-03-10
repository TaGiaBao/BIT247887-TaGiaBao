/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cmc.edu.vn.bai2;

/**
 *
 * @author Admin
 */
class Computer {
    private String HDD;
    private String RAM;

    private boolean isBluetoothEnabled;

    private Computer(ComputerBuilder builder) {
        this.HDD = builder.HDD;
        this.RAM = builder.RAM;
        this.isBluetoothEnabled = builder.isBluetoothEnabled;
    }

    @Override
    public String toString() {
        return "Computer [HDD=" + HDD + ", RAM=" + RAM + ", Bluetooth=" + isBluetoothEnabled + "]";
    }

    public static class ComputerBuilder {
        private String HDD;
        private String RAM;
        private boolean isBluetoothEnabled;

        public ComputerBuilder(String hdd, String ram) {
            this.HDD = hdd;
            this.RAM = ram;
        }

        public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

public class Bai2 {
    public static void main(String[] args) {
        Computer pcWithBT = new Computer.ComputerBuilder("500 GB", "16 GB")
                .setBluetoothEnabled(true)
                .build();

        Computer pcNoBT = new Computer.ComputerBuilder("1 TB", "8 GB")
                .setBluetoothEnabled(false)
                .build();

        System.out.println(pcWithBT);
        System.out.println(pcNoBT);
    }
}
