import FileSystem.AbstractFile;
import FileSystem.File;
import FileSystem.Folder;
import FileSystem.Root;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

public class FileExplorerGUI {
    private static AbstractFile currentDirectory;
    private static ArrayList<AbstractFile> createdFiles = new ArrayList<>();
    private static DefaultListModel<String> fileListModel;
    private static JList<String> fileList;

    public static void main(String[] args) {
        Root root = Root.getInstance();
        currentDirectory = Root.getInstance();

        JFrame frame = new JFrame("File Explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JButton backButton = new JButton("Back");
        panel.add(backButton);

        JButton createFolderButton = new JButton("Create Folder");
        panel.add(createFolderButton);

        JButton createFileButton = new JButton("Create File");
        panel.add(createFileButton);

        JButton goToFolderButton = new JButton("Go To Folder");
        panel.add(goToFolderButton);

        JButton deleteButton = new JButton("Delete");
        panel.add(deleteButton);

        JLabel currentDirectoryLabel = new JLabel("Current Directory: " + currentDirectory.getName());
        panel.add(currentDirectoryLabel);

        JTextField fileNameField = new JTextField(20);
        panel.add(fileNameField);

        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(400, 200));
        frame.pack();
        frame.setVisible(true);

        fileListModel = new DefaultListModel<>();
        fileList = new JList<>(fileListModel);
        JScrollPane scrollPane = new JScrollPane(fileList);
        panel.add(scrollPane);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentDirectory.getParent() != null || currentDirectory instanceof Root) {
                    currentDirectory = currentDirectory.getParent();
                    currentDirectoryLabel.setText("Current Directory: " + currentDirectory.getName());
                    updateFileList();
                }
            }
        });

        createFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = fileNameField.getText();
                String extension = JOptionPane.showInputDialog("Enter file extension, e.g., .txt:");
                try {
                    File newFile = new File(fileName, extension);
                    if (!(currentDirectory instanceof Root)) currentDirectory.addFile(newFile);
                    fileListModel.addElement(newFile.getName());
                    createdFiles.add(newFile);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        createFolderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String folderName = fileNameField.getText();
                try {
                    Folder newFolder;
                    if(currentDirectory instanceof Root){
                        newFolder =  new Folder(folderName);
                    }
                    else{
                        newFolder = new Folder(folderName, currentDirectory);
                    }
//                    if (!(currentDirectory instanceof Root)) currentDirectory.addFile(newFolder);
                    fileListModel.addElement(newFolder.getName());
                    createdFiles.add(newFolder);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        goToFolderButton.addActionListener(new ActionListener() {@Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = fileList.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedFileName = fileListModel.getElementAt(selectedIndex);
                for (AbstractFile file : currentDirectory.getFiles()) {
                    if (file.getName().equals(selectedFileName) && file instanceof Folder) {
                        currentDirectory = (Folder) file;
                        currentDirectoryLabel.setText("Current Directory: " + currentDirectory.getName());
                        updateFileList();
                        break;
                    }
                }
            }
        }
        });

        deleteButton.addActionListener(new ActionListener() {@Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = fileList.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedFileName = fileListModel.getElementAt(selectedIndex);
                for (AbstractFile file : currentDirectory.getFiles()) {
                    if (file.getName().equals(selectedFileName)) {
                        currentDirectory.deleteFile(selectedFileName);
                        currentDirectoryLabel.setText("Current Directory: " + currentDirectory.getName());
                        updateFileList();
                        break;
                    }
                }
            }
        }
        });

        fileList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int index = fileList.locationToIndex(e.getPoint());
                    String selectedFileName = fileListModel.getElementAt(index);
                    AbstractFile selectedFile = null;
                    for (AbstractFile file : currentDirectory.getFiles()) {
                        if (file.getName().equals(selectedFileName)) {
                            selectedFile = file;
                            break;
                        }
                    }
                    if (selectedFile instanceof Folder) {
                        currentDirectory = (Folder) selectedFile;
                        currentDirectoryLabel.setText("Current Directory: " + currentDirectory.getName());
                        updateFileList();
                    } else {
                        JPopupMenu popupMenu = new JPopupMenu();
                        JMenuItem deleteMenuItem = new JMenuItem("Delete");
                        deleteMenuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    currentDirectory.deleteFile(selectedFileName);
                                    fileListModel.removeElement(selectedFileName);
                                } catch (IllegalArgumentException ex) {
                                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        });
                        popupMenu.add(deleteMenuItem);
                        popupMenu.show(fileList, e.getX(), e.getY());
                    }
                }
            }
        });
    }

    private static void updateFileList() {
        fileListModel.clear();
        for (AbstractFile file : currentDirectory.getFiles()) {
            fileListModel.addElement(file.getName());
        }
    }
}