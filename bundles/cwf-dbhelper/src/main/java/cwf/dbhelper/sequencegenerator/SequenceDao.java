package cwf.dbhelper.sequencegenerator;

import cwf.dbhelper.sequencegenerator.SequenceException;

public interface SequenceDao {
	long getNextSequenceId(String key) throws SequenceException;
}