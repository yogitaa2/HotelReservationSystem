import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNo;
    String type;
    int price;
    boolean booked;

    Room(int roomNo, String type, int price) {
        this.roomNo = roomNo;
        this.type = type;
        this.price = price;
        booked = false;
    }
}

class Booking {
    String name;
    int roomNo;

    Booking(String name, int roomNo) {
        this.name = name;
        this.roomNo = roomNo;
    }
}

public class HotelReservationSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Booking> bookings = new ArrayList<>();

        // Rooms
        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));
        rooms.add(new Room(201, "Deluxe", 2500));
        rooms.add(new Room(202, "Deluxe", 2500));
        rooms.add(new Room(301, "Suite", 4000));

        int choice;

        do {
            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. Show Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Show Bookings");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            if (choice == 1) {

                System.out.println("\nAvailable Rooms:");

                for (Room room : rooms) {

                    if (room.booked == false) {
                        System.out.println(
                                room.roomNo + " - " +
                                        room.type + " - Rs." +
                                        room.price
                        );
                    }
                }

            } else if (choice == 2) {

                System.out.print("Enter room number: ");
                int roomNo = sc.nextInt();

                Room selectedRoom = null;

                for (Room room : rooms) {

                    if (room.roomNo == roomNo) {
                        selectedRoom = room;
                    }
                }

                if (selectedRoom == null) {

                    System.out.println("Room not found.");

                } else if (selectedRoom.booked) {

                    System.out.println("Room is already booked.");

                } else {

                    sc.nextLine();

                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();

                    System.out.println(
                            "Room price: Rs." + selectedRoom.price
                    );

                    System.out.print("Make payment? (yes/no): ");
                    String payment = sc.nextLine();

                    if (payment.equalsIgnoreCase("yes")) {

                        selectedRoom.booked = true;

                        Booking booking =
                                new Booking(name, roomNo);

                        bookings.add(booking);

                        System.out.println("Room booked successfully.");
                        System.out.println("Name: " + name);
                        System.out.println("Room: " + roomNo);

                    } else {

                        System.out.println("Booking cancelled.");
                    }
                }

            } else if (choice == 3) {

                System.out.print("Enter room number: ");
                int roomNo = sc.nextInt();

                boolean found = false;

                for (Room room : rooms) {

                    if (room.roomNo == roomNo && room.booked) {

                        room.booked = false;
                        found = true;

                        for (int i = 0; i < bookings.size(); i++) {

                            if (bookings.get(i).roomNo == roomNo) {
                                bookings.remove(i);
                                break;
                            }
                        }

                        System.out.println("Booking cancelled.");
                        break;
                    }
                }

                if (found == false) {
                    System.out.println("Booking not found.");
                }

            } else if (choice == 4) {

                if (bookings.size() == 0) {

                    System.out.println("No bookings available.");

                } else {

                    System.out.println("\nBookings:");

                    for (Booking booking : bookings) {

                        System.out.println(
                                "Name: " + booking.name +
                                        " | Room: " + booking.roomNo
                        );
                    }
                }

            } else if (choice == 5) {

                System.out.println("Thank you!");

            } else {

                System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}
