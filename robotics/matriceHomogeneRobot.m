function T = matriceHomogeneRobot(A, ALPHA, R, TETA)
    
    T = zeros(4,4);
    for i = 1:4
        T(i,i) = 1;
    end

    n = length(A);
    for i = 1:n
        
        if i==2
            TETA(i)=TETA(i)-(pi/2);
        end
        
        if i == 3 
            TETA(i)=TETA(i)+(pi/2);     
        end
        
        T = T * matriceHomogeneLiaision(A(i), ALPHA(i), R(i), TETA(i));
    end

end