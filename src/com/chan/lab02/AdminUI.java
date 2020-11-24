// Chan Wai Chi, 19060801d
package com.chan.lab02;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class AdminUI extends JFrame {
    private MyLinkedList<Book> bookList; // linked list to store book (database)
    private MyLinkedList<Book> tempList; // store bookList temporarily for edit and save operation
    private JTextField txtISBN; // text field of ISBN
    private JTextField txtTitle; // text field of title
    private AdminOperation operation = new AdminOperation();
    private String strISBN; // store the data in ISBN text field
    private DefaultTableModel model;
    private int clickISBNTime;
    private int clickTitleTime;

    public AdminUI() {
        clickISBNTime = 0;
        clickTitleTime = 0;
        Date dateCreated = new Date();
        bookList = new MyLinkedList<>();

        /* Declare GUI component */
        JTextArea txaDisplayAdmin = new JTextArea(
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
        txtISBN = new JTextField(15);
        txtTitle = new JTextField(15);
        JLabel lblISBN = new JLabel("ISBN: ");
        JLabel lblTitle = new JLabel("Title: ");
        JPanel pnlTxtBookRecord = new JPanel();
        JPanel pnlAction = new JPanel();
        JPanel pnlButton01 = new JPanel();
        JPanel pnlButton02 = new JPanel();
        model = new DefaultTableModel();

        /* Set the status of component */
        btnSave.setEnabled(false);

        /* Set table */
        JTable tblDisplayBook = new JTable(model);
        model.addColumn("ISBN");
        model.addColumn("Title");
        model.addColumn("Available");
        tblDisplayBook.setRowSelectionAllowed(true);
        tblDisplayBook.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        /* Add the GUI component into panels*/
        pnlTxtBookRecord.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        pnlTxtBookRecord.add(lblISBN);
        pnlTxtBookRecord.add(txtISBN);
        pnlTxtBookRecord.add(lblTitle);
        pnlTxtBookRecord.add(txtTitle);
        pnlButton01.setLayout(new FlowLayout(FlowLayout.CENTER, 10,
                15));
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
        add(pnlAction, BorderLayout.SOUTH);
        add(new JScrollPane(tblDisplayBook), BorderLayout.CENTER);
        add(txaDisplayAdmin, BorderLayout.NORTH);

        /* After clicking this button, exit the program */
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /* After clicking this button, check if the ISBN text field is empty OR
         * title text field is empty OR ISBN exist in the bookList.
         * If yes, display error message.
         * Otherwise, create Book object and add them to the bookList.
         * Display the updated table and clear the text field.
         */
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ISBN = txtISBN.getText();
                String title = txtTitle.getText();
                if (ISBN.isEmpty()) { // check if ISBN text field is empty
                    JOptionPane.showMessageDialog(new AdminUI(),
                            "The ISBN text field is empty");
                } else if (title.isEmpty()) { // check if title text field is empty
                    JOptionPane.showMessageDialog(new AdminUI(),
                            "The title text field is empty");
                } else if (operation.checkIfContains(ISBN, bookList) == true) { // check if input ISBN is in the linked list
                    JOptionPane.showMessageDialog(new AdminUI(),
                            operation.returnStrContainError(ISBN)
                    );
                } else {
                    bookList = operation.addBook(new Book(ISBN, title), bookList); // update the linked list
                    showDataFromList(bookList); // display all record in the table
                    clearTextField(); // clear text field
                }

                clickISBNTime = 0; // Reset the clicking time of "Display All by ISBN" button
                clickTitleTime = 0; // Reset the clicking time of "Display all by Title" button

            }
        });

        /* After clicking these button, append to the current linked list.
         * Display all book record in the table. Clear the text field.
         */
        btnLoadTestData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book HTMLBook = new Book("0131450913", "HTML How to Program");
                Book CplusplusBook = new Book("0131857576", "C++ How to Program");
                Book JavaBook = new Book("0132222205", "Java How to Program");

//                adminOperation.returnErrorMessage(HTMLBook, AdminOperation.ErrorType.CONTAIN);
                if (operation.checkIfContains(HTMLBook, bookList) == true) {
                    JOptionPane.showMessageDialog(new AdminUI(),
                            operation.returnStrContainError(HTMLBook),
                            "Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    bookList.addLast(HTMLBook);
                }

                if (operation.checkIfContains(CplusplusBook, bookList) == true) {
                    JOptionPane.showMessageDialog(new AdminUI(),
                            operation.returnStrContainError(CplusplusBook),
                            "Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    bookList.addLast(CplusplusBook);
                }

                if (operation.checkIfContains(JavaBook, bookList) == true) {
                    JOptionPane.showMessageDialog(new AdminUI(),
                            operation.returnStrContainError(JavaBook),
                            "Message", JOptionPane.ERROR_MESSAGE);
                } else {
                    bookList.addLast(JavaBook);
                }

                showDataFromList(bookList);
                clearTextField();
                clickISBNTime = 0; // Reset the clicking time of "Display All by ISBN" button
                clickTitleTime = 0; // Reset the clicking time of "Display all by Title" button
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
                strISBN = txtISBN.getText();
                if (bookList.size == 0) {
                    showErrorMessage("The database is empty", "Error");
                } else if (operation.checkIfContains(strISBN, bookList) == false) {
                    String errorMessage = operation.returnStrNotContainError(strISBN);
                    showErrorMessage(errorMessage, "Error");
                } else if (strISBN.isEmpty()) {
                    String errorMessage = "The text field is empty.";
                    showErrorMessage(errorMessage, "Error");
                } else {
                    /* get the title */
                    if (txtTitle.getText().isEmpty()) {
                        Book tempBook = operation.getBook(strISBN, bookList);
                        txtTitle.setText(tempBook.getTitle());
                    }

                    /* Enable "Save" button while disable other button */
                    btnSave.setEnabled(true);
                    btnAdd.setEnabled(false);
                    btnEdit.setEnabled(false);
                    btnSearch.setEnabled(false);
                    btnMore.setEnabled(false);
                    btnDelete.setEnabled(false);
                    btnDisplayAll.setEnabled(false);
                    btnDisplayAllByISBN.setEnabled(false);
                    btnDisplayAllByTitle.setEnabled(false);
                    btnLoadTestData.setEnabled(false);
                    btnExit.setEnabled(false);
                    tempList = bookList;
                }
                clickISBNTime = 0; // Reset the clicking time of "Display All by ISBN" button
                clickTitleTime = 0; // Reset the clicking time of "Display all by Title" button
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
                String oldISBN = strISBN;
                String newISBN = txtISBN.getText();
                String newTitle = txtTitle.getText();
// Check if the ISBN text field does not changed
                if (oldISBN.contains(newISBN)) {
                    showErrorMessage("The ISBN does not changed", "Message");
                }
                // Check if the linked list does not contains the ISBN
                else if (operation.checkIfContains(newISBN, bookList) == false) {
                    String errorMessage = operation.returnStrNotContainError(newISBN);
                    showErrorMessage(errorMessage, "Error");
                } else {
                    // TODO save the record, 23/11
                    tempList = operation.saveBook(oldISBN, newISBN, newTitle, bookList);
                    bookList = tempList;
                    showDataFromList(bookList); // display data from list
                    clearTextField(); // clear the text field

                    /* Disable "Save" button while enable other buttons */
                    btnSave.setEnabled(false);
                    btnAdd.setEnabled(true);
                    btnDelete.setEnabled(true);
                    btnEdit.setEnabled(true);
                    btnSearch.setEnabled(true);
                    btnMore.setEnabled(true);
                    btnLoadTestData.setEnabled(true);
                    btnDisplayAll.setEnabled(true);
                    btnDisplayAllByISBN.setEnabled(true);
                    btnDisplayAllByTitle.setEnabled(true);
                    btnExit.setEnabled(true);

                }
                clickISBNTime = 0; // Reset the clicking time of "Display All by ISBN" button
                clickTitleTime = 0; // Reset the clicking time of "Display all by Title" button
            }
        });

        /* After clicking this button, check if linked list is empty OR
         * bookList does not contain the input ISBN OR ISBN text field is empty
         * If yes, display error message. Otherwise, implement removeBook operation.
         * Display all records and clear the text field.
         */
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strISBN = txtISBN.getText();
                String errorMessage;
                // check if linked list is empty
                if (bookList.size == 0) {
                    errorMessage = "The database does not contain any record";
                    showErrorMessage(errorMessage, "Error");
                }
                // check if ISBN text field is empty
                else if (strISBN.isEmpty()) {
                    errorMessage = "The text field is empty";
                    showErrorMessage(errorMessage, "Error");
                }
                // check if linked list does not contain input ISBN
                else if (operation.checkIfContains(strISBN, bookList) == false) {
                    errorMessage = operation.returnStrNotContainError(strISBN);
                    showErrorMessage(errorMessage, "Error");
                }
                // TODO delete the record, 23/11
                else {
                    tempList = operation.removeBook(strISBN, bookList);
                    bookList = tempList;
                    showDataFromList(bookList);
                    clearTextField();
                }

                clickISBNTime = 0; // Reset the clicking time of "Display All by ISBN" button
                clickTitleTime = 0; // Reset the clicking time of "Display all by Title" button
            }
        });

        /* After clicking this button, check if text filed is not empty.
         * If yes, display records that contains the keyword in the ISBN text field or
         * the title text field. Otherwise, do nothing.
         */
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strISBN = txtISBN.getText();
                String strTitle = txtTitle.getText();

                // TODO search the given data in the linked list
                if (strISBN.isEmpty() && strTitle.isEmpty()) {
                    tempList = operation.searchBook(strISBN, strTitle, bookList);
                    showDataFromList(tempList);
                }

                clickISBNTime = 0; // Reset the clicking time of "Display All by ISBN" button
                clickTitleTime = 0; // Reset the clicking time of "Display all by Title" button
            }
        });

        /* After clicking this button, display all book record in
         * order of adding nodes to the bookList
         */
        btnDisplayAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDataFromList(bookList);
            }
        });

        /* After clicking this button, check if the clicking time.
         * If it is odd, display all book record in ascending order of Book ISBN.
         * If it is even, display all book record in descending order of Book ISBN.
         */
        btnDisplayAllByISBN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getRowCount() != 0) {
                    if (clickISBNTime % 2 == 0) { // odd clicking time
                        tempList = operation.sortISBNAscending(bookList);
                        showDataFromList(tempList);
                    } else { // even click time
                        tempList = operation.sortISBNDescending(bookList);
                        showDataFromList(tempList);
                    }
                    clickISBNTime++;
                }
            }
        });

        /* After clicking this button, check if the clicking time.
         * If it is odd, display all book record in ascending order of Book title.
         * If it is even, display all book record in descending order of Book title.
         */
        btnDisplayAllByTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.getRowCount() != 0) {
                    if (clickTitleTime % 2 == 0) {
                        tempList = operation.sortTitleAscending(bookList);
                        showDataFromList(tempList);
                    } else {
                        tempList = operation.sortTitleDescending(bookList);
                        showDataFromList(tempList);
                    }
                    clickTitleTime++;
                }
            }
        });

        /* After clicking this button, check if ISBN text field is empty OR
         * ISBN not exist in the linked list.
         * If yes, display the error message.
         * Otherwise, display a modal dialog page (BookControlUI).
         */
        btnMore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO get the book by using the ISBN, 23/11
                String ISBN = txtISBN.getText();
                String title = txtTitle.getText();
                // if ISBN text field is empty
                if (ISBN.isEmpty()) {
                    showErrorMessage("The ISBN text field is empty.",
                            "Error");
                }
                // if linked list does not contains the ISBN
                else if (operation.checkIfContains(ISBN, bookList) == false) {
                    String errorMessage = operation.returnStrNotContainError(ISBN);
                    showErrorMessage(errorMessage, "Error");
                } else {
                    Book book;
                    if (title.isEmpty()) {
                        book = operation.getBook(ISBN, bookList);
                    } else {
                        book = new Book(ISBN, title);
                    }
                    BookControlUI bookAction = new BookControlUI(book, new AdminUI());
                    bookAction.setTitle(txtTitle.getText());
                    bookAction.setSize(600, 500);
                    bookAction.setVisible(true);
                }

                clickISBNTime = 0; // Reset the clicking time of "Display All by ISBN" button
                clickTitleTime = 0; // Reset the clicking time of "Display all by Title" button
            }

        });

        /* After selecting a row on the table, get ISBN and title of this row
         * show them in the text field
         */
        tblDisplayBook.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowSelected = tblDisplayBook.getSelectedRow();
                TableModel tblModel = tblDisplayBook.getModel();
                txtISBN.setText(tblModel.getValueAt(rowSelected,
                        0).toString());
                txtTitle.setText(tblModel.getValueAt(rowSelected,
                        1).toString());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    /* Show a error message */
    public void showErrorMessage(String errorMessage, String title) {
        JOptionPane.showMessageDialog(new AdminUI(), errorMessage, title,
                JOptionPane.ERROR_MESSAGE);
    }

    /* Clear the text field */
    public void clearTextField() {
        txtISBN.setText("");
        txtTitle.setText("");
    }

    /* Get data from bookList and show this data in the table */
    public void showDataFromList(MyLinkedList<Book> list) {
        model.setRowCount(0);
        for (int i = 0; i < list.size; i++) {
            Book book = list.get(i);
            model.addRow(new Object[]{
                    book.getISBN(), book.getTitle(), book.isAvailable()
            });
        }
    }

    public static void main(String[] args) {
        AdminUI frame = new AdminUI();
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Library Admin System");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public class BookControlUI extends JDialog {
        private Book book;
        private String bookInfo;
        private JTextArea txaBookInfo;
        private JButton btnBorrow;
        private JButton btnReturn;
        private JButton btnReserve;
        private JButton btnWaitQueue;
        private JTextArea txaSystemMessage;

        public BookControlUI(Book passBook, JFrame frame) {
            super(frame, "", true);
            this.book = passBook;
            bookInfo = operation.returnStrBookInfo(book);

            /* Declaring GUI component */
            txaBookInfo = new JTextArea(bookInfo, 8, 30);
            btnBorrow = new JButton("Borrow");
            btnReturn = new JButton("Return");
            btnReserve = new JButton("Reserve");
            btnWaitQueue = new JButton("Waiting Queue");
            txaSystemMessage = new JTextArea(3, 30);
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
                        setButtonAvailable();
                    } else {
                        setButtonNotAvailable();
                    }
                }

                @Override
                public void windowClosing(WindowEvent e) {

                }

                @Override
                public void windowClosed(WindowEvent e) {
                    // TODO it should store modified book into the linked list
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

            /* borrow the book*/
            btnBorrow.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    book.setAvailable(false);
                    txaBookInfo.setText(operation.returnStrBookInfo(book));
                    txaSystemMessage.setText("The book is borrowed.");
                    if (book.isAvailable()) {
                        setButtonAvailable();
                    } else {
                        setButtonNotAvailable();
                    }
                }
            });

            /* After clicking this button, display message "The book is returned." at the bottom.
             * Call the returnBook operation. //TODO re-write the return description.
             * Otherwise, available set to false.
             */
            btnReturn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Book tempBook = book;
                    book = operation.returnBook(tempBook);
                    bookInfo = operation.returnStrBookInfo(book);
                    txaBookInfo.setText(bookInfo);

                    if (book.isAvailable()) {
                        setButtonAvailable();
                        setSystemMessage("The book is returned.");
                    } else {
                        setButtonNotAvailable();
                        setSystemMessage("The book is returned.\n" +
                                "The book is now borrowed by " + "" + ".");
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

            /* Click this button, then display the reserved queue at the bottom. */
            btnWaitQueue.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String strQueue = operation.returnWaitingQueue(book);
                    setSystemMessage(strQueue);
                }
            });
        }

        private void setSystemMessage(String message) {
            txaSystemMessage.setText(message);
        }

        private void setButtonAvailable() {
            btnBorrow.setEnabled(true);
            btnReserve.setEnabled(false);
            btnReturn.setEnabled(false);
            btnWaitQueue.setEnabled(false);
        }

        private void setButtonNotAvailable() {
            btnBorrow.setEnabled(false);
            btnReserve.setEnabled(true);
            btnReturn.setEnabled(true);
            btnWaitQueue.setEnabled(true);
        }
    }
}
