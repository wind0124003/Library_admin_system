package com.chan.lab02;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

public class BookControlUI extends JDialog {
    private Book book;
    private String bookInfo;

    public BookControlUI(Book book, JFrame frame) {
        super(frame, "", true);
        this.book = book;

        bookInfo = setBookInfo();

        /* Declaring GUI component */
        JTextArea txaBookInfo = new JTextArea(bookInfo, 8, 30);
        JButton btnBorrow = new JButton("Borrow");
        JButton btnReturn = new JButton("Return");
        JButton btnReserve = new JButton("Reserve");
        JButton btnWaitQueue = new JButton("Waiting Queue");
        JTextArea txaSystemMessage = new JTextArea(3, 30);
        JPanel pnlButton = new JPanel();
        JPanel pnlAll = new JPanel();

        /* Add the GUI Component into frame */
        pnlButton.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 30));
        pnlButton.add(btnBorrow);
        pnlButton.add(btnReturn);
        pnlButton.add(btnReserve);
        pnlButton.add(btnWaitQueue);

        pnlAll.setLayout(new BorderLayout());
        pnlAll.add(txaBookInfo, BorderLayout.NORTH);
        pnlAll.add(txaSystemMessage, BorderLayout.SOUTH);
        pnlAll.add(pnlButton, BorderLayout.CENTER);

        Container container = getContentPane();
        container.add(pnlAll);


        /* When this window is opened, set the status of the component */
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                if (book.isAvailable()) {
                    btnBorrow.setEnabled(true);
                    btnReserve.setEnabled(false);
                    btnReturn.setEnabled(false);
                    btnWaitQueue.setEnabled(false);
                } else {
                    btnBorrow.setEnabled(false);
                    btnReserve.setEnabled(true);
                    btnReturn.setEnabled(true);
                    btnWaitQueue.setEnabled(true);
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        /* Button control */
        btnBorrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                book.setAvailable(false);
                bookInfo = setBookInfo();
                txaBookInfo.setText(bookInfo);
                txaSystemMessage.setText("The book is borrowed.");
                if (book.isAvailable()) {
                    btnBorrow.setEnabled(true);
                    btnReserve.setEnabled(false);
                    btnReturn.setEnabled(false);
                    btnWaitQueue.setEnabled(false);
                } else {
                    btnBorrow.setEnabled(false);
                    btnReserve.setEnabled(true);
                    btnReturn.setEnabled(true);
                    btnWaitQueue.setEnabled(true);
                }
            }
        });

        /* After clicking this button, display message "The book is returned." at the bottom.
         * If no user in the reserved queue of the Book object, then
         * available set to true. "Borrow" button becomes enabled, and
         * all other button become disabled.
         * Otherwise, available set to false. The
         */
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = "";
                MyQueue<String> queue = book.getReservedQueue();

                if (queue == null) { // TODO:error nullPointerException
                    book.setAvailable(true);
                } else {
                    book.setAvailable(false);
                    name = queue.dequeue();
                    book.setReservedQueue(queue);
                }

                bookInfo = setBookInfo();
                txaBookInfo.setText(bookInfo);

                if (book.isAvailable()) {
                    btnBorrow.setEnabled(true);
                    btnReserve.setEnabled(false);
                    btnReturn.setEnabled(false);
                    btnWaitQueue.setEnabled(false);
                    txaSystemMessage.setText("The book is returned.");
                } else {
                    btnBorrow.setEnabled(false);
                    btnReserve.setEnabled(true);
                    btnReturn.setEnabled(true);
                    btnWaitQueue.setEnabled(true);
                    txaSystemMessage.setText("The book is returned.\n" +
                            "The book is now borrowed by " + name + ".");
                }
            }
        });

        /* Click this button, then pop up a showInputDialog.
         * The admin staff input the user name. And this name will be added to
         * reservedQueue of the Book object.
         * Then, display message "The book is reserved by [user's name]" at the bottom.
         */
        btnReserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // TODO: test function - btnWaitingQueue
        /* Click this button, then display the reserved queue at the bottom. */
        btnWaitQueue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder strQueue = new StringBuilder("The waiting queue:\n");
                MyQueue<String> queue = book.getReservedQueue();
                if (queue == null) {
                    strQueue = new StringBuilder("No people waiting for this book.");
                } else {
                    while (queue.getSize() != 0) {
                        strQueue.append(queue.dequeue() + "\n");
                    }
                }
                txaSystemMessage.setText(strQueue.toString());
            }
        });
    }

    /* Main method */
    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<>();
        queue.enqueue("Sam");
        queue.enqueue("Mary");
        queue.enqueue("Amy");

        Book book = new Book("C++ How to Program","0131857576",
                false, queue);

        System.out.println(book.getReservedQueue().toString());

        BookControlUI frame = new BookControlUI(book, null);
        frame.setTitle(book.getTitle());
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setVisible(true);


    }

    private String setBookInfo() {
        String bookInfo = "ISBN : " + book.getISBN() +
                "\nTitle : " + book.getTitle() +
                "\nAvailable : " + book.isAvailable();
        return bookInfo;
    }

}
