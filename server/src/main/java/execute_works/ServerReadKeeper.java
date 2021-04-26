package execute_works;

import collection_works.CollectionKeeper;

public interface ServerReadKeeper {

    void read();

    void setCollectionManager(CollectionKeeper collectionManager);
}
