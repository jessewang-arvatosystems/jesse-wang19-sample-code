package com.fdm.databases;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.TreeMap;

import com.fdm.accounts.Account;
import com.fdm.accounts.SetupAnAccount;
import com.fdm.helper.ConnectionHelper;

public class ByteIO_DTO {
	
	private String FILE_NAME = "resources/BankTellerByteOutput.txt";
	private ArrayList<Account> listOfAccounts = new ArrayList<Account>();
	private TreeMap<Integer, Account> accountInfo = new TreeMap<Integer,Account>();
	private SetupAnAccount setupAccount = SetupAnAccount.INSTANCE;

	@SuppressWarnings("unchecked")
	public TreeMap<Integer,Account> readByteFile() {
		
		InputStream is = null;
		ObjectInputStream in = null;
		
		try {
			is = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
			in = new ObjectInputStream(is);
			listOfAccounts = (ArrayList<Account>)in.readObject();
			in.close();
		}
		catch(ClassNotFoundException ex) {}
		catch(IOException ex) {}
		finally {
			ConnectionHelper.closeConnection(in);
		}
		
		return arrayListToTreeMap(listOfAccounts);
	}
	
	public void writeByteFile(TreeMap<Integer,Account> accountInfo) {
		
		ArrayList<Account> accountList = treeMapToArrayList(accountInfo);
		
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		
		// Very inelegant will think of better solution
		String absPath = getClass().getClassLoader().getResource(FILE_NAME).getFile();
		String srcPath = absPath.replaceFirst("/", "").replaceFirst("classes", "src").replace("/", "\\").replace("%20", " ");
		
		try {
			fos = new FileOutputStream(srcPath);
			out = new ObjectOutputStream(fos);
			out.writeObject(accountList);
			fos.close();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		finally {
			ConnectionHelper.closeConnection(out);
		}
	}
	
	private ArrayList<Account> treeMapToArrayList(TreeMap<Integer,Account> accountInfo) {
		
		listOfAccounts.clear();
		for (int key=1; key<=setupAccount.getAccountNumber(); key++) {
			if (accountInfo.containsKey(key)) {
				listOfAccounts.add(accountInfo.get(key));
			}
		}
		return listOfAccounts;
	}
	
	private TreeMap<Integer,Account> arrayListToTreeMap(ArrayList<Account> listOfAccounts) {
		
		for (Account account : listOfAccounts) {
			accountInfo.put(account.getAccountNumber(), account);
			setupAccount.setAccountNumber(account.getAccountNumber());
		}
		
		return accountInfo;
	}
}
