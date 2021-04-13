package com.promineotech.incidentReport;

import java.security.Key;
import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCrypt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@ComponentScan("com.promineotech.incidentReport")
@SpringBootApplication
public class App 
{
	public static void JWTStuff() {
		// JWT has: (a) header, (b) body (roles, token info), (c) signature (signed (that is, hashed then encrypted) header+body)
    	// the token has three parts, looks like Base64 encoded hashes separated by periods
    	// anyone can decode the header and payload, so unless you encrypt data there, don't put anything sensitive there.
    	
    	Key key = Keys.secretKeyFor( SignatureAlgorithm.HS256 );
    	
    	String jwt = Jwts.builder()
    					.claim( "role", "ADMIN" )
    					.setSubject( "PROMINEO TECH JWT" )  // retrievable with .getSubject(), below
    					.signWith( key )
    					.compact();
    	
    	System.out.println( "Here is a JWT:" );
    	System.out.println( jwt );
    	// eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJzdWIiOiJQUk9NSU5FTyBURUNIIEpXVCJ9.c2aeDNPBvWiq-UEsJ42h9K4Zs5EV9t8Vaf_fB28C8_s
    	// and if we check with jwt.io, it tells us the header alg and payload ("role", "sub")
    	
    	// Clearly we need to keep track of our secret key; else re-running creates new keys
    	
    	String encodedKey = Base64.getEncoder().encodeToString( key.getEncoded() );  // key.getEncoded() just returns the byte[] rep
    	
    	System.out.println( "Here is a server key which has been Base64 encoded:" );
    	System.out.println( encodedKey );
    	
    	byte[] decodedKey = Base64.getDecoder().decode( encodedKey ); // so decodedKey should equal key.getEncoded()
    	
    	// To authenticate
    	System.out.println( Jwts.parser().setSigningKey( decodedKey ).parseClaimsJws( jwt ).getBody().getSubject().equals( "PROMINEO TECH JWT" ) );
    	
    	// How you retrieve working key from stored text in db:
    	Key toMakeJWTsWith = Keys.hmacShaKeyFor( decodedKey );
	}
	
	public static void userAuthenticationStuff() {
    	// Generate first user for database (original pw for vetApi was "root")
    	String password = "sooperSekrit";
    	System.out.println( "Hash of the password '" + password + "' using system-generated salt:" );
    	String salt = BCrypt.gensalt();
    	
    	String hashAndSalt = BCrypt.hashpw( password, salt );
    	System.out.println( hashAndSalt );
    	
		// $2a$10$cesL.HbUrciOCeHYKMf15OQgbEJ3GLPJNPyxLbkCq0tththEyWrle
		//  \/ \/ \____________________/\_____________________________/
		//  |  |   22char B64-enc salt    31char B64-encoded hash
		//  |  |     (16 bytes)              (24 bytes)
		//  |  |
		//  |  cost ("10", so 2^10 rounds)
		//  |
		//  version ("2a", so "Bcrypt, UTF8-encoded pw, null terminated")
    	
    	System.out.println( BCrypt.checkpw( password, hashAndSalt ) );
	}
	


	
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
        JWTStuff();
    	System.out.println();
    	userAuthenticationStuff();
    }
}
