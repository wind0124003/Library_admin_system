// Chan Wai Chi, 19060801d
package com.chan.lab02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Iterator;

public class AdminUI extends JFrame {
    private MyLinkedList<Book> bookList;
    JFrame frame;

    public AdminUI() {
        Date dateCreated = new Date();
        bookList = new MyLinkedList<>();

        /* Declare GUI component */
        JTextArea txtDisplayAdmin = new JTextArea(
                "Student Name and ID: Chan Wai Chi (19060801d)\n" +
                        dateCreated, 6, 30);
        JButton btnAdd = new JButton("Add");
        JButton btnEdit = new JButton("Edit");
        JButton btnSave = new JButton("Save");
        JButton btnDelete = new JButton("Delete");
        JButton btnSearch = new JButton("Search");
        JButton btnMore = new JButton("More>>");
        JButton btnLoadTestData = new JButton("Load Test Data");
        JButton btnDisplayAll = new JButton("Display All");
        JButton btnDisplayAllByISBN = new JButton("Display All by ISBN");
        JButton btnDisplayAllByTitle = new JButton("Display All by Title");
        JButton btnExit = new JButton("Exit");
        JTable tblDisplayBook = new JTable();
        JScrollPane scrDisplayBook = new JScrollPane();
        JTextField txtISBN = new JTextField(15);
        JTextField txtTitle = new JTextField(15);
        JLabel lblISBN = new JLabel("ISBN: ");
        JLabel lblTitle = new JLabel("Title: ");
        JPanel pnlTxtBookRecord = new JPanel();
        JPanel pnlAction = new JPanel();
        JPanel pnlButton01 = new JPanel();
        JPanel pnlButton02 = new JPanel();

        /* Set the status of component */
        btnSave.setEnabled(false);

        /* Add the GUI component into panels*/
        pnlTxtBookRecord.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        pnlTxtBookRecord.add(lblISBN);
        pnlTxtBookRecord.add(txtISBN);
        pnlTxtBookRecord.add(lblTitle);
        pnlTxtBookRecord.add(txtTitle);
        pnlButton01.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        pnlButton01.add(btnAdd);
        pnlButton01.add(btnEdit);
        pnlButton01.add(btnSave);
        pnlButton01.add(btnDelete);
        pnlButton01.add(btnSearch);
        pnlButton01.add(btnMore);
        pnlButton02.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        pnlButton02.add(btnLoadTestData);
        pnlButton02.add(btnDisplayAll);
        pnlButton02.add(btnDisplayAllByISBN);
        pnlButton02.add(btnDisplayAllByTitle);
        pnlButton02.add(btnExit);
        pnlAction.setLayout(new GridLayout(3, 1));
        pnlAction.add(pnlTxtBookRecord);
        pnlAction.add(pnlButton01);
        pnlAction.add(pnlButton02);

        /* Add the GUI component into this UI page*/
        scrDisplayBook.add(tblDisplayBook);
        add(scrDisplayBook, BorderLayout.CENTER);
        add(txtDisplayAdmin, BorderLayout.NORTH);
        add(pnlAction, BorderLayout.SOUTH);

        /* After clicking this button, exit the program */
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /* After clicking this button,
         * check if the ISBN text field is empty OR
         * title text field is empty OR ISBN exist in the bookList.
         * If yes, display error message.
         * Otherwise, create Book object and add them to the bookList.
         * Display the updated table and clear the text field.
         */
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /* After clicking these button, append to the current linked list.
         * Display all book record in the table. Clear the text field.
         */
        btnLoadTestData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /* After clicking this button, check if bookList is empty OR
         * bookList does not contains the input ISBN.
         * If yes, display error message.
         * Otherwise, "Save" button becomes enabled and other button becomes disabled.
         */
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /* After clicking this button, check if no change in ISBN text field OR
         * bookList does not contain the modified error message.
         * If yes, save the modified information to Book object.
         * Display updated record in the table. Clear the text field.
         */
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /* After clicking this button, check if linked list is empty OR
         * bookList does not contain the input ISBN OR ISBN text field is not empty
         * If yes, display error message.
         * Otherwise, remove the selected book from the linked list
         * Display all records and clear the text field.
         */
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /* After clicking this button, check if text filed is not empty.
         * If yes, display records that contains the keyword in the ISBN text field or
         * the title text field.
         * Otherwise, do nothing.
         */
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /* After clicking this button, display all book record in
         * order of adding nodes to the bookList
         */
        btnDisplayAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /* After clicking this button, check if the clicking time.
         * If it is odd, display all book record in ascending order of Book ISBN.
         * If it is even, display all book record in descending order of Book ISBN.
         */
        btnDisplayAllByISBN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /* After clicking this button, check if the clicking time.
         * If it is odd, display all book record in ascending order of Book title.
         * If it is even, display all book record in descending order of Book title.
         */
        btnDisplayAllByTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        /* After clicking this button, check if ISBN text field is empty OR
         * ISBN is not existed in the linked list.
         * If yes, display the error message.
         * Otherwise, display a modal dialog page (BookControlUI).
         */
        btnMore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        AdminUI frame = new AdminUI();
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Library Admin System");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
