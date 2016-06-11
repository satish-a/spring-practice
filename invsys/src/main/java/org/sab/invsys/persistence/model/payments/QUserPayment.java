package org.sab.invsys.persistence.model.payments;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QUserPayment is a Querydsl query type for UserPayment
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserPayment extends EntityPathBase<UserPayment> {

    private static final long serialVersionUID = -2010828676;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QUserPayment userPayment = new QUserPayment("userPayment");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath notes = createString("notes");

    public final DateTimePath<java.util.Date> paymentDate = createDateTime("paymentDate", java.util.Date.class);

    public final StringPath paymentType = createString("paymentType");

    public final org.sab.invsys.persistence.model.user.QUser user;

    public QUserPayment(String variable) {
        this(UserPayment.class, forVariable(variable), INITS);
    }

    public QUserPayment(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUserPayment(PathMetadata<?> metadata, PathInits inits) {
        this(UserPayment.class, metadata, inits);
    }

    public QUserPayment(Class<? extends UserPayment> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new org.sab.invsys.persistence.model.user.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

