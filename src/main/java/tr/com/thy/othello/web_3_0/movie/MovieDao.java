package tr.com.thy.othello.web_3_0.movie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tr.com.thy.othello.web_3_0.common.AbstractDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


@Slf4j
@Repository
@Transactional
public class MovieDao extends AbstractDao
{
	
	public MovieDao( EntityManager entityManager, EntityManagerFactory entityManagerFactory )
	{
		super( entityManager, entityManagerFactory );
	}
	
	//@formatter:off
	@Query( "SELECT mov "
			+ " FROM Movie mov"
			+ " WHERE  users.oracleUsername = :username"
			+ " AND cp.partnerId = users.customerPartnerId" )
	//@formatter:on
	public Integer findAgentIntIdByUsername( String username )
	{
		
		//@formatter:off
		final TypedQuery< Integer > query = getQuery( "findAgentIntIdByUsername", Integer.class )
				.setParameter( "username", username )
				.setMaxResults( 1 );
		//@formatter:on
		
		try
		{
			return query.getSingleResult();
		}
		catch ( NoResultException e )
		{
			return null;
		}
	}
}
