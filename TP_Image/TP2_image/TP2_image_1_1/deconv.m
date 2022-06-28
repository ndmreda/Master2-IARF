clear all


h = [1 4 7; 2 5 8; 3 6 9];
x = round(5*rand(3,3));

% a)
prod_conv1 = conv2(h, x, 'full');

% %b)
[mh nh] = size(h);
[mx nx] = size(x);

m = mx + mh - 1;
n = nx + nh - 1;

H = fft2(h, m, n);
X = fft2(x, m, n);

prod_conv2 = real(ifft2(H.*X));
 
% %c)
prod_conv3 = imfilter(x, h, 'conv', 'circular');

% %d)
X = fft2(x);
center = round(size(h) / 2);
H = fft2(circshift(padarray(h, [mx - mh, nx - nh], 'post'), 1 - center))

prod_conv4 = real(ifft2(H.*X));

%
% %e)
x_reshaped =  reshape(x, [mx*nx , 1]);

% h_tild = fliplr(h)
% h_tild = flipud(h_tild)

hc = zeros(3,3);
hc = circshift(padarray(h, [mx - mh, nx - nh], 'post'), 1 - center);

H_BCCB = zeros(9,9) ;
H_BCCB(:,5) = reshape(h, [ mh*nh , 1]);
H_BCCB(5,:) = flip(reshape(h, [ mh*nh , 1]));

H_BCCB(2,4:6) = flip(H_BCCB(1:3,5)');
H_BCCB(1,4) = H_BCCB(2,5);
H_BCCB(3,4) = H_BCCB(1,5);
H_BCCB(1,6) = H_BCCB(2,4);
H_BCCB(3,6) = H_BCCB(2,5);

H_BCCB(4,4) = H_BCCB(5,5);
H_BCCB(6,6) = H_BCCB(5,5);
H_BCCB(6,4) = H_BCCB(4,5);
H_BCCB(4,6) = H_BCCB(6,5);

H_BCCB(8,4:6) = flip(H_BCCB(7:9,5)');
H_BCCB(7,4) = H_BCCB(8,5);
H_BCCB(9,4) = H_BCCB(7,5);
H_BCCB(7,6) = H_BCCB(8,4);
H_BCCB(9,6) = H_BCCB(8,5);

H_BCCB(1:3,1:3)=H_BCCB(4:6,4:6)
H_BCCB(7:9,7:9)=H_BCCB(4:6,4:6)

H_BCCB(4:6,7:9)=H_BCCB(1:3,4:6)
H_BCCB(7:9,1:3)=H_BCCB(1:3,4:6)

H_BCCB(4:6,1:3)=H_BCCB(7:9,4:6)
H_BCCB(1:3,7:9)=H_BCCB(7:9,4:6)


C_result = H_BCCB * x_reshaped
prod_conv3



