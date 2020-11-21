package com.chan.lab02;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

public class BookControlUI extends JFrame {
    private String ISBN;
    private String Title;
    private boolean available;
    private MyQueue<String> waitingQueue;
    private String bookInfo;


    public BookControlUI(Book book) {
        this.ISBN = book.getISBN();
        this.Title = book.getTitle();
        this.available = book.isAvailable();
        this.waitingQueue = book.getReservedQueue();

        bookInfo = setBookInfo();

        /* Declaring GUI component */
        JTextArea txaBookInfo = new JTextArea(bookInfo, 8, 30);
        JButton btnBorrow = new JButton("Borrow");
        JButton btnReturn = new JButton("Return");
        JButton btnReserve = new JButton("Reserve");
        JButton btnWaitQueue = new JButton("Waiting Queue");
        JTextArea txaSystemMessage = new JTextArea(3, 30);
        JPanel pnlButton = new JPanel();

        /* Add the GUI Component into frame */
        pnlButton.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 30));
        pnlButton.add(btnBorrow);
        pnlButton.add(btnReturn);
        pnlButton.add(btnReserve);
        pnlButton.add(btnWaitQueue);

        add(txaBookInfo, BorderLayout.NORTH);
        add(txaSystemMessage, BorderLayout.SOUTH);
        add(pnlButton, BorderLayout.CENTER);

        /* When this window is opened, set the status of the component */
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                if (available) {
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
                available = false;
                bookInfo = setBookInfo();
                txaBookInfo.setText(bookInfo);
                txaSystemMessage.setText("The book is borrowed.");
                if (available) {
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
                bookInfo = setBookInfo();
                txaBookInfo.setText(bookInfo);

                if (waitingQueue.getSize() == 0) {
                    available = true;
                } else {
                    available = false;
                    name = waitingQueue.dequeue();
                }

                if (available) {
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
                MyQueue<String> queue = waitingQueue;
                if (queue.getSize() == 0)
                {
                    strQueue = new StringBuilder("No people waiting for this book.");
                }
                else {
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
        Book book = new Book("0131857576", "C++ How to Program", true,
                new MyQueue<>());
        BookControlUI frame = new BookControlUI(book);
        frame.setTitle(frame.Title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setVisible(true);
    }

    private String setBookInfo() {
        String bookInfo = "ISBN : " + ISBN + "\nTitle : " + Title +
                "\nAvailable : " + available;
        return bookInfo;
    }

}
