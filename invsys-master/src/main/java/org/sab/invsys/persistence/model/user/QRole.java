package org.sab.invsys.persistence.model.user;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QRole is a Querydsl query type for Role
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRole extends EntityPathBase<Role> {

    private static final long serialVersionUID = 1539170295;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QRole role1 = new QRole("role1");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> role = createNumber("role", Integer.class);

    public final QUser user;

    public QRole(String variable) {
        this(Role.class, forVariable(variable), INITS);
    }

    public QRole(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRole(PathMetadata<?> metadata, PathInits inits) {
        this(Role.class, metadata, inits);
    }

    public QRole(Class<? extends Role> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

