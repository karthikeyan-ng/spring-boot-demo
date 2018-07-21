/**
 * 
 */
package com.techstack.spring.programmaticTransactionManagement;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Application code that must execute in a transactional context, and that will use the TransactionTemplate explicitly, 
 * looks like the following. You, as an application developer, write a TransactionCallback implementation (typically 
 * expressed as an anonymous inner class) that contains the code that you need to execute in the context of a transaction. 
 * You then pass an instance of your custom TransactionCallback to the execute(..) method exposed on the TransactionTemplate.
 * 
 * @author KARTHIKEYAN N
 *
 */
public class SimpleService {
	
	// single TransactionTemplate shared amongst all methods in this instance
    private final TransactionTemplate transactionTemplate;

    // use constructor-injection to supply the PlatformTransactionManager
    public SimpleService(PlatformTransactionManager transactionManager) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }
    
    /**
     * Specifying transaction settings
     * 
     * You can specify transaction settings such as the propagation mode, the isolation level, the timeout, and 
     * so forth on the TransactionTemplate either programmatically or in configuration. TransactionTemplate instances 
     * by default have the default transactional settings. The following example shows the programmatic customization of 
     * the transactional settings for a specific TransactionTemplate:
     */
    public SimpleService(String otherValue, PlatformTransactionManager transactionManager) {
    	
    	this.transactionTemplate = new TransactionTemplate(transactionManager);

        // the transaction settings can be set here explicitly if so desired
        this.transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
        this.transactionTemplate.setTimeout(30); // 30 seconds
        // and so forth...
    }

    @SuppressWarnings("unchecked")
	public Object someServiceMethod() {
        return transactionTemplate.execute(new TransactionCallback() {
            // the code in this method executes in a transactional context
            public Object doInTransaction(TransactionStatus status) {
                updateOperation1();
                return resultOfUpdateOperation2();
            }
        });
    }
    
    /**
     * If there is no return value, use the convenient TransactionCallbackWithoutResult class with an anonymous class as follows:
     */
    public void someServiceMethod1() {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                updateOperation1();
                updateOperation2();
            }
        });
    }
    
    /**
     * Code within the callback can roll the transaction back by calling the setRollbackOnly() method on the 
     * supplied TransactionStatus object:
     */
    public void exampleForRollback() {
    	transactionTemplate.execute(new TransactionCallbackWithoutResult() {

    	    protected void doInTransactionWithoutResult(TransactionStatus status) {
    	        try {
    	        	updateOperation3();
    	        	updateOperation4();
    	        } catch (SomeBusinessExeption ex) {
    	            status.setRollbackOnly();
    	        }
    	    }
    	});
    }

	protected Object resultOfUpdateOperation2() {
		return null;
	}

	protected void updateOperation1() {
		
	}
	
	protected void updateOperation2() {
		
	}

    protected void updateOperation3() throws SomeBusinessExeption {
    	
    }
    
    protected void updateOperation4() throws SomeBusinessExeption {
    	
    }
}
