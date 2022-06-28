function T = matriceHomogeneRobot(A, ALPHA, R, TETA)
    
    T = zeros(4,4);
    for i = 1:4
        T(i,i) = 1;
    end

    n = length(A);
    for i = 1:n
        T = T * matriceHomogeneLiaision(A(i), ALPHA(i), R(i), TETA(i));
    end

end