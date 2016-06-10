package org.sab.invsys.persistence.model.user;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QUserBalance is a Querydsl query type for UserBalance
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserBalance extends EntityPathBase<UserBalance> {

    private static final long serialVersionUID = -580866576;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QUserBalance userBalance = new QUserBalance("userBalance");

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath notes = createString("notes");

    public final QUser user;

    public QUserBalance(String variable) {
        this(UserBalance.class, forVariable(variable), INITS);
    }

    public QUserBalance(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserBalance(PathMetadata<?> metadata, PathInits inits) {
        this(UserBalance.class, metadata, inits);
    }

    public QUserBalance(Class<? extends UserBalance> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

