import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.util.regex.Pattern;
import javax.swing.*;
import java.awt.event.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
public class AdFrame extends JFrame implements ActionListener{

    AddUser add = new AddUser();
    Users userob = new Users();
    GetDetails get = new GetDetails();
    ModifyDetails modifyob = new ModifyDetails();
    DeleteUser del = new DeleteUser();
    UploadUsers up = new UploadUsers();

    JPanel createPanel,modifyPanel,deletePanel,usersPanel;
    JButton create,modify,delete,users;

    JLabel createTitle,createDname,createLogon,createPassword;
    JTextField TcreateDname,TcreateLogon;
    JPasswordField JcreatePassword;
    JButton createUser,uploadUsers,chooseFile;
    JTextArea textArea;

    JLabel modifyTitle,username,fname,lname,in,logon,dname,job,office,dept,pnum,email,webpage,pno,city,state,pcode;
    JTextField Tusername,Tfname,Tlname,Tin,Tlogon,Tdname,Tjob,Toffice,Tdept,Tpnum,
        Temail,Twebpage,Tpno,Tcity,Tstate,Tpcode;
    JButton modifyUser,getUser;

    JLabel deleteTitle,deleteDname;
    JTextField TdeleteDname;
    JButton deleteUser;

    JLabel usersTitle,itr,Lusername,Lfname,Llname,Lin,Llogon,Ldname,Ljob,Loffice,Ldept,Lpnum,
        Lemail,Lwebpage,Lpno,Lcity,Lstate,Lpcode;
    JPanel panel;
    GridBagConstraints gbc;
    JButton next,back,export;
    JScrollPane scrollPane;
    JComboBox<Integer> list;
    JFileChooser chooser,uploadFile;

    String userNames[] = new String[100],userRecords[][] = new String[100][15],usersArray[][] = new String[50][17];
    int start = 0,end = 0,num=5,usersCount=0;
    Integer numCount[] = {5,8,10,15};
    
    public AdFrame() {
        super("Active Directory");
        setSize(1400, 800);

        create = new JButton("CREATE");
        create.setBounds(10, 20, 100, 20);

        modify = new JButton("MODIFY");
        modify.setBounds(10, 50, 100, 20);

        delete = new JButton("DELETE");
        delete.setBounds(10, 80, 100, 20);

        users = new JButton("USERS");
        users.setBounds(10, 110, 100, 20);

        add(create);
        add(modify);
        add(delete);
        add(users);

        create.addActionListener(this);
        modify.addActionListener(this);
        delete.addActionListener(this);
        users.addActionListener(this);

        /*Create Panel*/
        createPanel = new JPanel(null);
        createPanel.setBounds(150, 0, 1000, 800);
        createPanel.setVisible(true);

        createTitle = new JLabel("CREATE");
        createTitle.setFont(new Font("Serif", Font.BOLD, 20));
        createDname = new JLabel("Display Name : ");
        createLogon = new JLabel("sAMAccountName : ");
        createPassword = new JLabel("Password : ");

        TcreateDname = new JTextField(25);
        TcreateLogon = new JTextField(25);
        JcreatePassword = new JPasswordField(25);

        createUser = new JButton("Add");
        chooseFile = new JButton("Browse");
        uploadUsers = new JButton("Upload");
        createUser.addActionListener(this);
        chooseFile.addActionListener(this);
        uploadUsers.addActionListener(this);

        textArea = new JTextArea ();
        JScrollPane scroll = new JScrollPane (textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        textArea.setEditable(false);

        createTitle.setBounds(250, 0, 200, 30);
        createDname.setBounds(80, 50, 200, 30);
        createLogon.setBounds(80, 90, 200, 30);
        createPassword.setBounds(80, 130, 200, 30);

        TcreateDname.setBounds(300, 50, 200, 30);
        TcreateLogon.setBounds(300, 90, 200, 30);
        JcreatePassword.setBounds(300, 130, 200, 30);

        createUser.setBounds(250, 190, 100, 30);
        chooseFile.setBounds(400, 190, 100, 30);
        uploadUsers.setBounds(550, 190, 100, 30);

        scroll.setBounds(80, 250, 800, 400);

        uploadUsers.setEnabled(false);

        createPanel.add(createTitle);
        createPanel.add(createDname);
        createPanel.add(createLogon);
        createPanel.add(createPassword);
        createPanel.add(TcreateDname);
        createPanel.add(TcreateLogon);
        createPanel.add(JcreatePassword);
        createPanel.add(createUser);
        createPanel.add(chooseFile);
        createPanel.add(uploadUsers);
        createPanel.add(scroll);

        add(createPanel);

        /*Modify Panel*/
        modifyPanel = new JPanel(null);
        modifyPanel.setBounds(150, 0, 800, 800);
        modifyPanel.setVisible(false);

        modifyTitle = new JLabel("MODIFY");
        modifyTitle.setFont(new Font("Serif", Font.BOLD, 20));
        username = new JLabel("Username : ");
        fname = new JLabel("First Name : ");
        lname = new JLabel("Last Name : ");
        in = new JLabel("Initials : ");
        logon = new JLabel("sAMAccountName : ");
        dname = new JLabel("Display Name : ");
        job = new JLabel("Job : ");
        office = new JLabel("Office : ");
        dept = new JLabel("Department : ");
        pnum = new JLabel("Phone Number : ");
        email = new JLabel("Email id : ");
        webpage = new JLabel("Webpage : ");
        pno = new JLabel("PostBox No : ");
        city = new JLabel("City : ");
        state = new JLabel("State : ");
        pcode = new JLabel("Pincode : ");

        Tusername = new JTextField(25);
        Tfname = new JTextField(25);
        Tlname = new JTextField(25);
        Tin = new JTextField(25);
        Tlogon = new JTextField(25);
        Tdname = new JTextField(25);
        Tjob = new JTextField(25);
        Toffice = new JTextField(25);
        Tdept = new JTextField(25);
        Tpnum = new JTextField(25);
        Temail = new JTextField(25);
        Twebpage = new JTextField(25);
        Tpno = new JTextField(25);
        Tcity = new JTextField(25);
        Tstate = new JTextField(25);
        Tpcode = new JTextField(25);

        modifyUser = new JButton("Modify");
        getUser = new JButton("Submit");
        
        modifyUser.addActionListener(this);
        getUser.addActionListener(this);

        modifyTitle.setBounds(250, 0, 200, 30);
        username.setBounds(80, 30, 200, 30);
        fname.setBounds(80, 70, 200, 30);
        lname.setBounds(80, 110, 200, 30);
        in.setBounds(80, 150, 200, 30);
        logon.setBounds(80, 190, 200, 30);
        dname.setBounds(80, 230, 200, 30);
        job.setBounds(80, 270, 200, 30);
        office.setBounds(80, 310, 200, 30);
        dept.setBounds(80, 350, 200, 30);
        pnum.setBounds(80, 390, 200, 30);
        email.setBounds(80, 430, 200, 30);
        webpage.setBounds(80, 470, 200, 30);
        pno.setBounds(80, 510, 200, 30);
        city.setBounds(80, 550, 200, 30);
        state.setBounds(80, 590, 200, 30);
        pcode.setBounds(80, 630, 200, 30);

        Tusername.setBounds(300, 30, 200, 30);
        Tfname.setBounds(300, 70, 200, 30);
        Tlname.setBounds(300, 110, 200, 30);
        Tin.setBounds(300, 150, 200, 30);
        Tlogon.setBounds(300, 190, 200, 30);
        Tdname.setBounds(300, 230, 200, 30);
        Tjob.setBounds(300, 270, 200, 30);
        Toffice.setBounds(300, 310, 200, 30);
        Tdept.setBounds(300, 350, 200, 30);
        Tpnum.setBounds(300, 390, 200, 30);
        Temail.setBounds(300, 430, 200, 30);
        Twebpage.setBounds(300, 470, 200, 30);
        Tpno.setBounds(300, 510, 200, 30);
        Tcity.setBounds(300, 550, 200, 30);
        Tstate.setBounds(300, 590, 200, 30);
        Tpcode.setBounds(300, 630, 200, 30);

        modifyUser.setBounds(250, 690, 100, 30);
        getUser.setBounds(550, 30, 100, 30);

        modifyPanel.add(modifyTitle);
        modifyPanel.add(username);
        modifyPanel.add(Tusername);
        modifyPanel.add(fname);
        modifyPanel.add(Tfname);
        modifyPanel.add(lname);
        modifyPanel.add(Tlname);
        modifyPanel.add(in);
        modifyPanel.add(Tin);
        modifyPanel.add(logon);
        modifyPanel.add(Tlogon);
        modifyPanel.add(dname);
        modifyPanel.add(Tdname);
        modifyPanel.add(job);
        modifyPanel.add(Tjob);
        modifyPanel.add(office);
        modifyPanel.add(Toffice);
        modifyPanel.add(dept);
        modifyPanel.add(Tdept);
        modifyPanel.add(pnum);
        modifyPanel.add(Tpnum);
        modifyPanel.add(email);
        modifyPanel.add(Temail);
        modifyPanel.add(webpage);
        modifyPanel.add(Twebpage);
        modifyPanel.add(pno);
        modifyPanel.add(Tpno);
        modifyPanel.add(city);
        modifyPanel.add(Tcity);
        modifyPanel.add(state);
        modifyPanel.add(Tstate);
        modifyPanel.add(pcode);
        modifyPanel.add(Tpcode);

        modifyPanel.add(modifyUser);
        modifyPanel.add(getUser);

        add(modifyPanel);

        /*Delete Panel*/
        deletePanel = new JPanel(null);
        deletePanel.setBounds(150, 0, 500, 500);
        deletePanel.setVisible(false);

        deleteTitle = new JLabel("DELETE");
        deleteTitle.setFont(new Font("Serif", Font.BOLD, 20));
        deleteDname = new JLabel("Display Name : ");
        TdeleteDname = new JTextField(25);

        deleteUser = new JButton("Delete");
        
        deleteUser.addActionListener(this);

        deleteTitle.setBounds(250, 0, 200, 30);
        deleteDname.setBounds(80, 30, 200, 30);
        TdeleteDname.setBounds(300, 30, 200, 30);

        deleteUser.setBounds(250, 90, 100, 30);

        deletePanel.add(deleteTitle);
        deletePanel.add(deleteDname);
        deletePanel.add(TdeleteDname);

        deletePanel.add(deleteUser);

        add(deletePanel);

        /*Users Panel*/
        panel = new JPanel();  
        panel.setLayout(new GridBagLayout());
        scrollPane = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(1200, 400));
 
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(30,70,0,0);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;

        usersPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,150,50));

        back = new JButton("Prev");
        next = new JButton("Next");
        export = new JButton("Export");

        list = new JComboBox<Integer>(numCount);
        list.setActionCommand("SIZE");

        back.addActionListener(this);
        next.addActionListener(this);
        list.addActionListener(this);
        export.addActionListener(this);

        usersPanel.add(back);
        usersPanel.add(next);
        usersPanel.add(list);
        usersPanel.add(export);
        usersPanel.add(scrollPane);
        usersPanel.setVisible(false);
        
        add(usersPanel);

    }

    public void setTitleRow(){
        panel.removeAll();

        JLabel temp;
        Font font = new Font("Serif", Font.BOLD, 20);

        gbc.gridx = 1;  
        gbc.gridy = 0;
        temp = new JLabel("Username");
        temp.setFont(font);
        panel.add(temp, gbc);

        gbc.gridx = 2;
        temp = new JLabel("First Name");
        temp.setFont(font);
        panel.add(temp, gbc);

        gbc.gridx = 3;
        temp = new JLabel("Last Name");
        temp.setFont(font);
        panel.add(temp, gbc);

        gbc.gridx = 4;
        temp = new JLabel("Initials");
        temp.setFont(font);
        panel.add(temp, gbc);

        gbc.gridx = 5;
        temp = new JLabel("Logon Name");
        temp.setFont(font);
        panel.add(temp, gbc);

        gbc.gridx = 6;
        temp = new JLabel("Display Name");
        temp.setFont(font);
        panel.add(temp, gbc);

        gbc.gridx = 7;
        temp = new JLabel("Job");
        temp.setFont(font);
        panel.add(temp, gbc);

        gbc.gridx = 8;
        temp = new JLabel("Office");
        temp.setFont(font);
        panel.add(temp, gbc);

        gbc.gridx = 9;
        temp = new JLabel("Department");
        temp.setFont(font);
        panel.add(temp, gbc);

        gbc.gridx = 10;
        temp = new JLabel("Phone Number");
        temp.setFont(font);
        panel.add(temp, gbc);

        gbc.gridx = 11;
        temp = new JLabel("Email");
        temp.setFont(font);
        panel.add(temp, gbc);

        gbc.gridx = 12;
        temp = new JLabel("Webpage");
        temp.setFont(font);
        panel.add(temp, gbc);

        gbc.gridx = 13;
        temp = new JLabel("PostBox No");
        temp.setFont(font);
        panel.add(temp, gbc);

        gbc.gridx = 14;
        temp = new JLabel("City");
        temp.setFont(font);
        panel.add(temp, gbc);

        gbc.gridx = 15;
        temp = new JLabel("State");
        temp.setFont(font);
        panel.add(temp, gbc);

        gbc.gridx = 16;
        temp = new JLabel("Pincode");
        temp.setFont(font);
        panel.add(temp, gbc);

        for (int i = start; (i<end && userNames[i]!=null); i++) {
            userNames[i] = userNames[i].replace("CN=","");

            gbc.gridx = 0;  
            gbc.gridy = i+1;

            JButton bitr = new JButton("Modify");
            bitr.setActionCommand(userNames[i]);
            bitr.addActionListener(this);
            panel.add(bitr, gbc);

            gbc.gridx = 1;  
            gbc.gridy = i+1;  
            panel.add(new JLabel(userNames[i]), gbc);

            for (int j=1;j<=15;j++ ) {

                gbc.gridx = j+1;  
                gbc.gridy = i+1;  
                panel.add(new JLabel(userRecords[i][j-1]), gbc);
                    
            }
        }
    }

    public void actionPerformed(ActionEvent ae) {

        String option = ae.getActionCommand();

        if(option.equals("CREATE")){
            
            modifyPanel.setVisible(false);
            deletePanel.setVisible(false);
            usersPanel.setVisible(false);
            createPanel.setVisible(true);
        }
        else if(option.equals("MODIFY")){

            Tusername.setText("");
            Tfname.setText("");
            Tlname.setText("");
            Tin.setText("");
            Tlogon.setText("");
            Tdname.setText("");
            Tjob.setText("");
            Toffice.setText("");
            Tdept.setText("");
            Tpnum.setText("");
            Temail.setText("");
            Twebpage.setText("");
            Tpno.setText("");
            Tcity.setText("");
            Tstate.setText("");
            Tpcode.setText("");

            createPanel.setVisible(false);
            deletePanel.setVisible(false);
            usersPanel.setVisible(false);
            modifyPanel.setVisible(true);
        }
        else if(option.equals("DELETE")){
            
            createPanel.setVisible(false);
            modifyPanel.setVisible(false);
            usersPanel.setVisible(false);
            deletePanel.setVisible(true);
        }
        else if(option.equals("USERS") || option.equals("SIZE")){

            userNames = userob.usersList();

            for (int i=0; userNames[i]!=null;i++ ) {
                userNames[i] = userNames[i].replace("CN=","");
                userRecords[i] = get.userDetails(userNames[i]);
            }

            int val = (int) list.getSelectedItem();
            num = val;
            
            start = 0;
            end = num;
            
            setTitleRow();

            back.setEnabled(false);
            next.setEnabled(true);

            panel.revalidate();
            
            createPanel.setVisible(false);
            modifyPanel.setVisible(false);
            deletePanel.setVisible(false);
            usersPanel.setVisible(true);
        }
        else if(option.equals("Prev")){
            
            start -= num;
            end -= num;

            if(start >= 0){
                setTitleRow();
                next.setEnabled(true);
            }
            if(start-num < 0)
                back.setEnabled(false);

            panel.revalidate();
        }
        else if(option.equals("Next")){

            start += num;
            end += num;

            if(userNames[start]!=null){
                setTitleRow();
                back.setEnabled(true);
            }
            if(userNames[start+num] == null)
                next.setEnabled(false);

            panel.revalidate();
        }
        else if(option.equals("Add")){
            
            String displayName = TcreateDname.getText();
            String sAMAccountName = TcreateLogon.getText();
            String password = JcreatePassword.getText();

            if(add.addUser(displayName,sAMAccountName,password)){
                JOptionPane.showMessageDialog(null,"Succeed");
                TcreateDname.setText("");
                TcreateLogon.setText("");
                JcreatePassword.setText("");
            }
            else
                JOptionPane.showMessageDialog(null,"Failed", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else if(option.equals("Browse")){
            uploadFile = new JFileChooser(); 
            uploadFile.setCurrentDirectory(new File("."));
            uploadFile.setPreferredSize(new Dimension(800, 600));
            uploadFile.setDialogTitle("Upload");
             
            if (uploadFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                String errorString = "";
                try {
                    File file = uploadFile.getSelectedFile();
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);

                    int i=0;
                    usersCount=0;
                    String line = "";
                    String[] tempArr;
                    Boolean mailMatcher,numMatcher;
                    String mailRegex = "^[\\w]+(\\.[\\w]+)*@[\\w]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
                    String numRegex = "^\\d{10}";

                    while ((line = br.readLine()) != null) {
                        tempArr = line.split(",");
                        if (i!=0) {
                            mailMatcher = Pattern.matches(mailRegex, tempArr[11]);
                            numMatcher = Pattern.matches(numRegex, tempArr[10]);
                            
                            if(mailMatcher && numMatcher){
                                usersArray[usersCount++] = tempArr;
                            }
                            else if(mailMatcher){
                                errorString += "ERROR : "+tempArr[0]+" could not loaded.Invalid Mobile Number\n";
                            }
                            else if(numMatcher){
                                errorString += "ERROR : "+tempArr[0]+" could not loaded.Invalid Email Id\n";
                            }
                        }
                        i++;
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                if(!errorString.equals(""))
                    JOptionPane.showMessageDialog(null, errorString, "ERROR", JOptionPane.ERROR_MESSAGE);
                uploadUsers.setEnabled(true);
            }
        }
        else if(option.equals("Upload")){
            String errMsg[] =  up.uploadUserRecords(usersArray, usersCount);
            for (int i=0;errMsg[i]!=null ;i++ ) {
                textArea.append("ERROR : The object "+errMsg[i]+" is not created.The Username or Logon name already exists.\n");
            }
            if(errMsg[0] != null)
                textArea.append("\n-----------------------------------------------------------------------------------------------------\n");
            uploadUsers.setEnabled(false);
        }
        else if(option.equals("Export")){
            
            chooser = new JFileChooser(); 
            chooser.setCurrentDirectory(new File("."));
            chooser.setPreferredSize(new Dimension(800, 600));
            chooser.setDialogTitle("Save");
             
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
                
                Document document = new Document();
                try
                {
                    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(chooser.getSelectedFile()));
                    document.open();
             
                    PdfPTable table = new PdfPTable(16);
                    table.setTotalWidth(1800);
                    table.setLockedWidth(true);
                    table.setSpacingBefore(10f);
                    table.setSpacingAfter(10f);

                    Paragraph header = new Paragraph();
                    header.add("User Details");
                    header.setAlignment(Element.ALIGN_CENTER);
                    document.add(header);

                    PdfPCell cell;

                    com.itextpdf.text.Font f = new com.itextpdf.text.Font();
                    f.setStyle(Font.BOLD);
                    f.setSize(15);

                    cell = new PdfPCell();
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(new Phrase("Username",f));
                    table.addCell(cell);

                    cell = new PdfPCell();
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(new Phrase("First Name",f));
                    table.addCell(cell);

                    cell = new PdfPCell();
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(new Phrase("Last Name",f));
                    table.addCell(cell);

                    cell = new PdfPCell();
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(new Phrase("Initials",f));
                    table.addCell(cell);

                    cell = new PdfPCell();
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(new Phrase("Logon Name",f));
                    table.addCell(cell);

                    cell = new PdfPCell();
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(new Phrase("Display Name",f));
                    table.addCell(cell);

                    cell = new PdfPCell();
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(new Phrase("Job",f));
                    table.addCell(cell);

                    cell = new PdfPCell();
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(new Phrase("Office",f));
                    table.addCell(cell);

                    cell = new PdfPCell();
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(new Phrase("Department",f));
                    table.addCell(cell);

                    cell = new PdfPCell();
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(new Phrase("Phone Number",f));
                    table.addCell(cell);

                    cell = new PdfPCell();
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(new Phrase("Email",f));
                    table.addCell(cell);

                    cell = new PdfPCell();
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(new Phrase("Webpage",f));
                    table.addCell(cell);

                    cell = new PdfPCell();
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(new Phrase("PostBox No",f));
                    table.addCell(cell);

                    cell = new PdfPCell();
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(new Phrase("City",f));
                    table.addCell(cell);

                    cell = new PdfPCell();
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(new Phrase("State",f));
                    table.addCell(cell);

                    cell = new PdfPCell();
                    cell.setBorder(Rectangle.NO_BORDER);
                    cell.addElement(new Phrase("Pincode",f));
                    table.addCell(cell);
                    
                    for (int i = 0; userNames[i]!=null; i++) {
                        cell = new PdfPCell();
                        cell.setBorder(Rectangle.NO_BORDER);
                        cell.addElement(new Phrase(userNames[i]));
                        table.addCell(cell); 
                        for (int j=0; j<15 ; j++) {
                            cell = new PdfPCell();
                            cell.setBorder(Rectangle.NO_BORDER);
                            cell.addElement(new Phrase(userRecords[i][j]));
                            table.addCell(cell);    
                        }           
                    }

                    PdfContentByte canvas = writer.getDirectContent();
                    PdfTemplate template = canvas.createTemplate(table.getTotalWidth(), table.getTotalHeight());
                    table.writeSelectedRows(0, -1, 0, table.getTotalHeight(), template);
                    Image img = Image.getInstance(template);
                    img.scaleToFit(500, PageSize.A4.getHeight());
                    document.add(img);
             
                    document.close();
                    writer.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        else if(option.equals("Submit")){

            String tuser = Tusername.getText();

            int i=-1;

            userNames = userob.usersList();

            for (int j=0; userNames[j]!=null;j++ ) {
                userNames[j] = userNames[j].replace("CN=","");
                if(userNames[j].equals(tuser))
                    i = j;
                userRecords[j] = get.userDetails(userNames[j]);
            }
            
            if(i != -1){
                Tfname.setText(userRecords[i][0]);
                Tlname.setText(userRecords[i][1]);
                Tin.setText(userRecords[i][2]);
                Tlogon.setText(userRecords[i][3]);
                Tdname.setText(userRecords[i][4]);
                Tjob.setText(userRecords[i][5]);
                Toffice.setText(userRecords[i][6]);
                Tdept.setText(userRecords[i][7]);
                Tpnum.setText(userRecords[i][8]);
                Temail.setText(userRecords[i][9]);
                Twebpage.setText(userRecords[i][10]);
                Tpno.setText(userRecords[i][11]);
                Tcity.setText(userRecords[i][12]);
                Tstate.setText(userRecords[i][13]);
                Tpcode.setText(userRecords[i][14]);
            }
        }
        else if(option.equals("Modify")){
            
            modifyob.modifyUser(Tusername.getText(),Tfname.getText(),Tlname.getText(),Tin.getText(),Tlogon.getText(),
                Tdname.getText(),Tjob.getText(),Toffice.getText(),Tdept.getText(),Tpnum.getText(),Temail.getText(),
                Twebpage.getText(),Tpno.getText(),Tcity.getText(),Tstate.getText(),Tpcode.getText());

            JOptionPane.showMessageDialog(null,"Modified Successfully");

            for (int i=0; userNames[i]!=null;i++ ) {
                userRecords[i] = get.userDetails(userNames[i]);
            }

            int val = (int) list.getSelectedItem();
            num = val;
            
            start = 0;
            end = num;
            
            setTitleRow();

            back.setEnabled(false);
            next.setEnabled(true);

            panel.revalidate();
            
            createPanel.setVisible(false);
            modifyPanel.setVisible(false);
            deletePanel.setVisible(false);
            usersPanel.setVisible(true);

            Tusername.setText("");
            Tfname.setText("");
            Tlname.setText("");
            Tin.setText("");
            Tlogon.setText("");
            Tdname.setText("");
            Tjob.setText("");
            Toffice.setText("");
            Tdept.setText("");
            Tpnum.setText("");
            Temail.setText("");
            Twebpage.setText("");
            Tpno.setText("");
            Tcity.setText("");
            Tstate.setText("");
            Tpcode.setText("");
        }
        else if(option.equals("Delete")){
            
            if(del.delUser(TdeleteDname.getText())){
                JOptionPane.showMessageDialog(null,"Deleted Successfully");
                TdeleteDname.setText("");
            }
            else
                JOptionPane.showMessageDialog(null,"Failed", "ERROR", JOptionPane.ERROR_MESSAGE);

            userNames = userob.usersList();

            for (int i=0; userNames[i]!=null;i++ ) {
                userNames[i] = userNames[i].replace("CN=","");
                userRecords[i] = get.userDetails(userNames[i]);
            }
        }
        else{

            int i=0;
            for (i=0; userNames[i]!=null;i++ ) {
                if(option.equals(userNames[i]))
                    break;
            }

            Tusername.setText(option);
            Tfname.setText(userRecords[i][0]);
            Tlname.setText(userRecords[i][1]);
            Tin.setText(userRecords[i][2]);
            Tlogon.setText(userRecords[i][3]);
            Tdname.setText(userRecords[i][4]);
            Tjob.setText(userRecords[i][5]);
            Toffice.setText(userRecords[i][6]);
            Tdept.setText(userRecords[i][7]);
            Tpnum.setText(userRecords[i][8]);
            Temail.setText(userRecords[i][9]);
            Twebpage.setText(userRecords[i][10]);
            Tpno.setText(userRecords[i][11]);
            Tcity.setText(userRecords[i][12]);
            Tstate.setText(userRecords[i][13]);
            Tpcode.setText(userRecords[i][14]);

            createPanel.setVisible(false);
            deletePanel.setVisible(false);
            usersPanel.setVisible(false);
            modifyPanel.setVisible(true);
        }
    }

    public static void main(String[] args) {
        AdFrame frame = new AdFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}