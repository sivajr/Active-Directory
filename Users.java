import java.util.Enumeration;
import java.util.Hashtable;
import javax.naming.*;
import javax.naming.directory.*;

public class Users {

	public String[] usersList() {
		String[] user = new String[100];
		try {
			Hashtable<Object, Object> properties = new Hashtable<Object, Object>();

			properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			properties.put(Context.PROVIDER_URL, "ldap://localhost:389");
			properties.put(Context.SECURITY_AUTHENTICATION,"simple");
			properties.put(Context.SECURITY_PRINCIPAL,"cn=Administrator,cn=Users,dc=admin,dc=local"); 
			properties.put(Context.SECURITY_CREDENTIALS,"Siva123123");
			DirContext context = new InitialDirContext(properties);

			String filter = "(objectClass=User)";
			String[] attrIDs = {"cn"};
			SearchControls searchCtrls = new SearchControls();
			searchCtrls.setReturningAttributes(attrIDs);
			searchCtrls.setSearchScope(SearchControls.ONELEVEL_SCOPE);

			NamingEnumeration values = context.search("cn=users,dc=admin,dc=local",filter,searchCtrls);

			int itr=0;
			while (values.hasMoreElements())
			{
				SearchResult result = (SearchResult) values.next();
				Attributes attribs = result.getAttributes();

				NamingEnumeration ae = attribs.getAll();
				Attribute atr = (Attribute) ae.next();

				Enumeration vals = atr.getAll();
				
				user[itr++] = (String) vals.nextElement();
			}

			context.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}